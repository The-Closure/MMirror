package org.closure.MMirror.services;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import com.google.common.collect.ImmutableList;

import org.apache.commons.collections4.IteratorUtils;
import org.closure.MMirror.Exceptions.UserException;
import org.closure.MMirror.entities.Event;
import org.closure.MMirror.entities.User;
import org.closure.MMirror.models.EventDto;
import org.closure.MMirror.repositories.EventRepo;
import org.closure.MMirror.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventService {
    
    @Autowired
    EventRepo eventRepo;

    @Autowired
    UserRepo userRepo;

    public EventDto addEvent(String userID, EventDto event) throws Exception
    {
        User user = userRepo.findById(userID).orElseThrow(()-> new UserException("no user with this id..."));
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
    }

    public List<EventDto> getEvents(String userID) throws UserException
    {
       List<String> eventIds = userRepo.findById(userID).orElseThrow(()-> new UserException("no user with this id...")).getEvents().stream().map((e)-> e.getId()).collect(Collectors.toList());
       return  IteratorUtils.toList(eventRepo.findAllById(eventIds).iterator()).stream().map((e)-> new EventDto().end(e.getEnd()).id(e.getId()).start(e.getStart()).title(e.getTitle()).summery(e.getSummery())).collect(Collectors.toList());
    //    return  IteratorUtils.toList(eventRepo.findAllById(eventIds).iterator()).stream().map((e)-> new EventDto().end(e.getEnd().toString()).id(e.getId()).start(e.getStart().toString()).title(e.getTitle()).summery(e.getSummery())).collect(Collectors.toList());
       // return eventRepo.findAllById(eventIds).iterator().forEachRemaining((new ArrayList<String>())::add);
    }


}
