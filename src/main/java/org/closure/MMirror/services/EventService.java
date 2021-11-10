package org.closure.MMirror.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.swing.text.DateFormatter;

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
import org.closure.MMirror.models.google_event.End;
import org.closure.MMirror.models.google_event.GoogleEvent;
import org.closure.MMirror.models.google_event.Start;
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
    final String eventValue = "{\"start\": {\"dateTime\": \"2021-09-09T04:00:00+01:00\" },\"end\": {\"dateTime\": \"2021-09-09T05:00:00+01:00\"}   }";
    @Autowired
    EventRepo eventRepo;

    @Autowired
    UserRepo userRepo;

    public EventDto addEvent(String userID, EventDto event)
            throws EventException, JsonMappingException, JsonProcessingException {
        User user = userRepo.findById(userID).orElseThrow(() -> new UserException("no user with this id..."));
        if (!user.isGoogle_account()) {
            event.start(event.getStart().replace("/", "-"));
            event.end(event.getEnd().replace("/", "-"));
            Event entity = new Event().id(IdGeneration.getNextRandomString()).summery(event.getSummery())
                    .title(event.getTitle()).user(user).start(event.getStart()).end(event.getEnd());
            entity = eventRepo.save(entity);
            return event.id(entity.getId()).user_id(userID).user_name(user.getName());
        } else {
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.set("Authorization", "Bearer " + user.getGoogle_token());
            httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

            LocalDateTime startDate = LocalDateTime.parse(event.getStart());
            String startDateString = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH':'mm':'ss'+01:00'").format(startDate);

            LocalDateTime endDate = LocalDateTime.parse(event.getEnd());
            String endDateString = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH':'mm':'ss'+01:00'").format(endDate);
            GoogleEvent googleEvent = new GoogleEvent();
            googleEvent.start(new Start().dateTime(event.getStart())).end(new End().dateTime(event.getEnd()))
                    .summary(event.getSummery());
            System.out.println(googleEvent.toString());
            HttpEntity<String> request = new HttpEntity<String>(
                    "{ \"summary\": \"" + event.getSummery() + "\", \"start\": { \"dateTime\":\"" + startDateString
                            + "\" }, \"end\": { \"dateTime\": \"" + endDateString + "\" }  }",
                    httpHeaders);
            RestTemplate restTemplate = new RestTemplate();
            GoogleEvent e = restTemplate.postForObject(
                    "https://www.googleapis.com/calendar/v3/calendars/primary/events", request, GoogleEvent.class);

            System.out.println("response id : " + e.getSummary());
            eventRepo.save(new Event().summery(e.getSummary()).title(e.getSummary())
                    .start(e.getStart() != null ? e.getStart().getDateTime() : "null")
                    .end(e.getEnd() != null ? e.getEnd().getDateTime() : "null").user(user).id(e.getId()));

        }
        return new EventDto();
    }

    public List<EventDto> getEvents(String userID) throws UserException {
        List<String> eventIds = userRepo.findById(userID)
                .orElseThrow(() -> new UserException("no user with this id...")).getEvents().stream()
                .map((e) -> e.getId()).collect(Collectors.toList());
        return IteratorUtils
                .toList(eventRepo.findAllById(eventIds).iterator()).stream().map((e) -> new EventDto().end(e.getEnd())
                        .id(e.getId()).start(e.getStart()).title(e.getTitle()).summery(e.getSummery()))
                .collect(Collectors.toList());
        // return
        // IteratorUtils.toList(eventRepo.findAllById(eventIds).iterator()).stream().map((e)->
        // new
        // EventDto().end(e.getEnd().toString()).id(e.getId()).start(e.getStart().toString()).title(e.getTitle()).summery(e.getSummery())).collect(Collectors.toList());
        // return eventRepo.findAllById(eventIds).iterator().forEachRemaining((new
        // ArrayList<String>())::add);
    }

}
