package org.closure.MMirror.services;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.commons.collections4.IteratorUtils;
import org.closure.MMirror.Exceptions.EventException;
import org.closure.MMirror.Exceptions.UserException;
import org.closure.MMirror.entities.Event;
import org.closure.MMirror.entities.User;
import org.closure.MMirror.models.EventDto;
import org.closure.MMirror.models.GoogleEvents;
import org.closure.MMirror.repositories.EventRepo;
import org.closure.MMirror.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class EventService {
    
    @Autowired
    EventRepo eventRepo;

    @Autowired
    UserRepo userRepo;

    public EventDto addEvent(String userID, EventDto event) throws EventException, JsonMappingException, JsonProcessingException
    {
        User user = userRepo.findById(userID).orElseThrow(()-> new UserException("no user with this id..."));
        if(!user.isGoogle_account()){
        event.start(event.getStart().replace("/", "-"));
        event.end(event.getEnd().replace("/", "-"));
        Event entity = new Event()
            .id(IdGeneration.getNextRandomString())
            .summery(event.getSummery())
            .title(event.getTitle())
            .user(user)
            .start(event.getStart())
            .end(event.getEnd());
        entity = eventRepo.save(entity);
        return event.id(entity.getId());
    }else{
            HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.add("Authorization", "Bearer " + user.getGoogle_token());
			httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
			HttpEntity<String> httpEntity = new HttpEntity<>(httpHeaders);
			RestTemplate restTemplate = new RestTemplate();
        	ResponseEntity<String> responseEntity = restTemplate.exchange("https://www.googleapis.com/calendar/v3/calendars/primary/events",
            HttpMethod.POST, httpEntity, String.class);
            if(responseEntity.hasBody())
            {
                if(responseEntity.getBody().contains("UNAUTHENTICATED"))
                {
                    throw new EventException("401");
                }else {
                    GoogleEvents googleEvents = new ObjectMapper().readValue(responseEntity.getBody(), GoogleEvents.class);
                    googleEvents.getItems();
                }

            }
				
        }
        return new EventDto();
    }

    public List<EventDto> getEvents(String userID) throws UserException
    {
       List<String> eventIds = userRepo.findById(userID).orElseThrow(()-> new UserException("no user with this id...")).getEvents().stream().map((e)-> e.getId()).collect(Collectors.toList());
       return  IteratorUtils.toList(eventRepo.findAllById(eventIds).iterator()).stream().map((e)-> new EventDto().end(e.getEnd()).id(e.getId()).start(e.getStart()).title(e.getTitle()).summery(e.getSummery())).collect(Collectors.toList());
    //    return  IteratorUtils.toList(eventRepo.findAllById(eventIds).iterator()).stream().map((e)-> new EventDto().end(e.getEnd().toString()).id(e.getId()).start(e.getStart().toString()).title(e.getTitle()).summery(e.getSummery())).collect(Collectors.toList());
       // return eventRepo.findAllById(eventIds).iterator().forEachRemaining((new ArrayList<String>())::add);
    }


}
