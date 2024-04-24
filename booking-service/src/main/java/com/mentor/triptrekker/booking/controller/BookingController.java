package com.mentor.triptrekker.booking.controller;

import com.mentor.triptrekker.booking.model.BookingDataEntity;
import com.mentor.triptrekker.booking.service.RabbitMQSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/booking")
public class BookingController {
    @Autowired
    private RabbitMQSender rabbitMQSender;

    @PostMapping("/send")
    public String sendMessage(@RequestBody BookingDataEntity bookingDataEntity) {
        rabbitMQSender.sendMessage("your_exchange_name", "your_routing_key", bookingDataEntity);
        return "Message sent successfully";
    }
}
