package com.galvanize.motel666.service;

import com.galvanize.motel666.entity.UserEvent;
import com.galvanize.motel666.repository.UserEventRepo;
import com.galvanize.motel666.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserEventService {

    private final UserEventRepo repo;

    @Autowired
    public UserEventService(UserEventRepo repo) {
        this.repo = repo;
    }

    public void saveLog(UserEvent userEvent) {
        repo.save(userEvent);
    }

    public Iterable<UserEvent> allEventsBetweenDate(String dateFrom, String dateTo) {
        Date dFrom = DateUtil.getDate(dateFrom);
        Date dTo = DateUtil.getDate(dateTo);
        return repo.findAllByTimestampBetween(dFrom, dTo);
    }

    public Iterable<UserEvent> allEventsByUserId(String userId) {
        return repo.findAllByUserId(userId);
    }
}
