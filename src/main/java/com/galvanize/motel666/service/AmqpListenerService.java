package com.galvanize.motel666.service;

import com.galvanize.motel666.entity.UserEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AmqpListenerService {
    private static final Logger LOGGER = LoggerFactory.getLogger(AmqpListenerService.class);

    private final UserEventService userEventService;

    @Autowired
    public AmqpListenerService(UserEventService userEventService) {
        this.userEventService = userEventService;
    }

    @RabbitListener(queues = "${amqp.logging.queue}")
    public void verifyListener(final UserEvent event) {
        LOGGER.info("Received logging message: {} from motel-666.", event);
        userEventService.saveLog(event);
    }
}
