package org.closure.MMirror.config;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.closure.MMirror.entities.Event;
import org.closure.MMirror.entities.User;
import org.closure.MMirror.models.google_event.GoogleEvent;
import org.closure.MMirror.models.GoogleEvents;
import org.closure.MMirror.repositories.EventRepo;
import org.closure.MMirror.repositories.UserRepo;
import org.closure.MMirror.services.IdGeneration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


@Component("oauth2authSuccessHandler")
public class Oauth2AuthenticationSuccessHandler implements AuthenticationSuccessHandler {

	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();;
	@Autowired
	private OAuth2AuthorizedClientService authorizedClientService;
	@Autowired
	private UserRepo userRepository;

	@Autowired
	private EventRepo eventRepo;


	
	


	User u = null;
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
	Authentication authentication) throws IOException, ServletException {
		OAuth2AuthenticationToken authenticationToken = (OAuth2AuthenticationToken) authentication;
		String firstName = authenticationToken.getPrincipal().getAttributes().get("name").toString();
		OAuth2AuthorizedClient auth2AuthorizedClient = authorizedClientService.loadAuthorizedClient(authenticationToken.getAuthorizedClientRegistrationId(), authenticationToken.getPrincipal().getName());
		if ((u = userRepository.findByName(firstName)) == null) {
			String email = authenticationToken.getPrincipal().getAttributes().get("email").toString();
			String token = auth2AuthorizedClient.getAccessToken().getTokenValue();
			User user = new User().id(IdGeneration.getNextRandomString()).email(email).name(firstName).google_account(true).google_token(token).is_in(true).is_active(true);		
			u = userRepository.save(user);
			HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.add("Authorization", "Bearer " + auth2AuthorizedClient.getAccessToken().getTokenValue());
			httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
			HttpEntity<String> httpEntity = new HttpEntity<>(httpHeaders);
			RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<GoogleEvents> responseEntity = restTemplate.exchange("https://www.googleapis.com/calendar/v3/calendars/primary/events",
                HttpMethod.GET, httpEntity, GoogleEvents.class);
				if(responseEntity.hasBody())
				{
					List<GoogleEvent> events = responseEntity.getBody().getItems();
					// events.forEach((e) -> {
					// 	System.out.println("summery : "+e.getStart() != null ? e.getStart().getDateTime() : "null");
					// });
					eventRepo.saveAll(events.stream().map((e)->{
						Event event = new Event().summery(e.getSummary()).title(e.getSummary()).start(e.getStart() != null ? e.getStart().getDateTime() : "null").end(e.getEnd() != null ? e.getEnd().getDateTime() : "null").user(userRepository.findById(u.getId()).get()).id(IdGeneration.getNextRandomString());
						
						return event;
					}).collect(Collectors.toList()));
				}
			
		}
		request.getSession().setAttribute("clientID", u.getId());
		this.redirectStrategy.sendRedirect(request, response,"/events");
	}
	
}
