package com.magdy.rabbitmqdemo.repositories;

import com.magdy.rabbitmqdemo.entities.Subscriber;
import com.magdy.rabbitmqdemo.enums.EventType;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SubscriberRepository  extends CrudRepository<Subscriber, Integer> {
    List<Subscriber> findAllByEventType(EventType eventType);

}
