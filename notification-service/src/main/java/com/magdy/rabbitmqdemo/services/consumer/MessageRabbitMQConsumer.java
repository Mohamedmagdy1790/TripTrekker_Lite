package com.magdy.rabbitmqdemo.services.consumer;


import com.magdy.rabbitmqdemo.dto.NotificationMessage;
import com.magdy.rabbitmqdemo.services.NotificationService;
import jakarta.mail.MessagingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class MessageRabbitMQConsumer {
    public NotificationService notificationService;



    @Autowired
    public MessageRabbitMQConsumer(NotificationService notificationService) {
        this.notificationService = notificationService;
    }



    @RabbitListener(queues = {"${rabbitmq.queue.name}"})
    public void consume(NotificationMessage notificationMessage) throws MessagingException {

        log.info("received message -> notification id : " + notificationMessage.getNotificationId () +" booking id : "+ notificationMessage.getBookingId () );

        notificationService.sendEmail (notificationMessage);

    }



}
