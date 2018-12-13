package com.galvanize.motel666.controller;

import com.galvanize.motel666.entity.UserEvent;
import com.galvanize.motel666.service.UserEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/events")
public class UserEventController {

    private final UserEventService eventService;

    @Autowired
    public UserEventController(UserEventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping("/date/between/{dateFrom}/{dateTo}")
    public Iterable<UserEvent> allEventsBetweenDate(@PathVariable String dateFrom,
                                                    @PathVariable String dateTo) {
        return eventService.allEventsBetweenDate(dateFrom, dateTo);
    }

    @GetMapping("/user/{userId}")
    public Iterable<UserEvent> allEventsByUserId(@PathVariable String userId) {
        return eventService.allEventsByUserId(userId);
    }

}
