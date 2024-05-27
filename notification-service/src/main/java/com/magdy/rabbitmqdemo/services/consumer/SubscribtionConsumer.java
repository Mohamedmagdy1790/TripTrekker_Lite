package com.magdy.rabbitmqdemo.services.consumer;

import com.magdy.rabbitmqdemo.dto.MessageForSubscribers;
import com.magdy.rabbitmqdemo.entities.Subscriber;
import com.magdy.rabbitmqdemo.enums.EventType;
import com.magdy.rabbitmqdemo.repositories.SubscriberRepository;
import com.magdy.rabbitmqdemo.services.NotificationService;
import jakarta.mail.MessagingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@Slf4j
public class SubscribtionConsumer {

    public NotificationService notificationService;
    public SubscriberRepository subscriberRepository;

    SubscribtionConsumer(NotificationService notificationService, SubscriberRepository subscriberRepository) {
        this.notificationService = notificationService;
        this.subscriberRepository = subscriberRepository;
    }
    // todo  send event type with the message to make it generic
    @RabbitListener(queues = {"${rabbitmq.queue.name.discount}"})
    public void consumeDiscount(MessageForSubscribers message) throws MessagingException {

        log.info("received message -> for discount event " + message.getMessage () );

        // todo here we will add event type
        notifySubscribers(message.getEventtype(),message.getMessage());


    }

    private void notifySubscribers(EventType eventType, String message) {


        // get list of subscribers of certain event_type
        List<Subscriber> subscriberList= subscriberRepository.findAllByEventType (eventType);
        //send meassege to each one of them
        subscriberList.forEach(subscriber -> {
            System.out.println(message);
            try {
                notificationService.sendEmailForSubscribers(subscriber.getEmail(),message);

            } catch (MessagingException e) {
                throw new RuntimeException (e);
            }
        });
    }
}
