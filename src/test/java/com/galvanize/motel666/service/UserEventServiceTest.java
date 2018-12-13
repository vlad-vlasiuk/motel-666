package com.galvanize.motel666.service;

import com.galvanize.motel666.entity.UserEvent;
import com.galvanize.motel666.repository.UserEventRepo;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserEventServiceTest {

    @MockBean
    UserEventRepo repo;

    @MockBean
    AmqpListenerService amqpListenerService;

    @Autowired
    UserEventService service;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void saveLog() {
        UserEvent mockUserEvent = new UserEvent();
        when(repo.save(any())).thenReturn(new UserEvent());
        service.saveLog(mockUserEvent);
        verify(repo, times(1)).save(any());
    }

    @Test
    public void allEventsBetweenDate() {
        String mockDateFrom = "2018-10-10";
        String mockDateTo = "2018-11-10";

        when(repo.findAllByTimestampBetween(any(), any())).thenReturn(new ArrayList<>());
        service.allEventsBetweenDate(mockDateFrom, mockDateTo);
        verify(repo, times(1)).findAllByTimestampBetween(any(),any());
    }

    @Test
    public void allEventsByUserId() {
        String mockUserId = "1b468f7d-79e8-40c1-80a2-0dd7226e7771";

        when(repo.findAllByUserId(any())).thenReturn(new ArrayList<>());
        service.allEventsByUserId(mockUserId);
        verify(repo, times(1)).findAllByUserId(any());
    }
}