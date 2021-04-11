package org.closure.MMirror.controllers;

import java.util.List;
import java.util.stream.Stream;

import javax.servlet.ServletRequest;

import org.closure.MMirror.models.EventDto;
import org.closure.MMirror.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v2/events")
public class EventCotroller {

    @Autowired
    EventService eventService;

    @RequestMapping("/addevent")
    public ResponseEntity<String> addEvent(@RequestBody EventDto event, ServletRequest request)
    {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(eventService.addEvent(request.getParameter("userID"), event).toString());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    } 
    
    @RequestMapping("/getevents/{userID}")
    public ResponseEntity getEvents(@PathVariable String userID)
    {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(eventService.getEvents(userID));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.OK).body(Stream.of(e.getMessage()).toList());
        }
    }
}
