package com.mentor.triptrekker.booking.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mentor.triptrekker.booking.model.BookingDataEntity;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private ObjectMapper objectMapper; // Jackson ObjectMapper for JSON serialization

    public void sendMessage(String exchangeName, String routingKey, BookingDataEntity bookingDataEntity) {
        try {
            String jsonMessage = objectMapper.writeValueAsString(bookingDataEntity);
            rabbitTemplate.convertAndSend(exchangeName, routingKey, jsonMessage);
            System.out.println("Message sent successfully: " + jsonMessage);
        } catch (Exception e) {
            System.err.println("Failed to serialize message: " + e.getMessage());
        }
    }
}