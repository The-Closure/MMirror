package org.closure.MMirror.config;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.closure.MMirror.entities.User;
import org.closure.MMirror.repositories.UserRepo;
import org.closure.MMirror.services.IdGeneration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.client.web.HttpSessionOAuth2AuthorizedClientRepository;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;


@Component("oauth2authSuccessHandler")
public class Oauth2AuthenticationSuccessHandler implements AuthenticationSuccessHandler {

	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();;
	@Autowired
	private OAuth2AuthorizedClientService authorizedClientService;
	@Autowired
	private UserRepo userRepository;

	@Autowired
	private PasswordEncoder encoder;


	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
										Authentication authentication) throws IOException, ServletException {
		OAuth2AuthenticationToken authenticationToken = (OAuth2AuthenticationToken) authentication;
		authenticationToken.getPrincipal().getAttributes().forEach((key, value)->System.out.println(key+" : "+value));
		String firstName = null;
		OAuth2AuthorizedClient auth2AuthorizedClient = authorizedClientService.loadAuthorizedClient(authenticationToken.getAuthorizedClientRegistrationId(), authenticationToken.getPrincipal().getName());
		System.out.println("!!!!! access token is : "+auth2AuthorizedClient.getAccessToken().getTokenValue());
		
		if (userRepository.findByName(authentication.getName()) == null) {
			firstName = authenticationToken.getPrincipal().getAttributes().get("name").toString();
			String email = authenticationToken.getPrincipal().getAttributes().get("email").toString();
			String token = auth2AuthorizedClient.getAccessToken().getTokenValue();
			User user = new User().id(IdGeneration.getNextRandomString()).email(email).name(firstName).google_account(true).google_token(token).is_in(true).is_active(true);		
			userRepository.save(user);
		}
		this.redirectStrategy.sendRedirect(request, response,firstName!=null?"/home?name="+firstName:"/home");
	}
	
}
