package com.mentor.triptrekker.booking.service;

import com.mentor.triptrekker.booking.config.BookingRabbitMQConfig;
import com.mentor.triptrekker.booking.config.PaymentRabbitMQConfig;
import com.mentor.triptrekker.booking.enums.BookingStatus;
import com.mentor.triptrekker.booking.model.BookingDataEntity;
import com.mentor.triptrekker.booking.repository.BookingRepository;
import com.mentor.triptrekker.booking.request.FlightBookingData;
import org.modelmapper.ModelMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookingService {
    @Autowired
    private RabbitMQSender rabbitMQSender;
    private BookingRepository bookingRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public BookingService(BookingRepository bookingRepository, ModelMapper modelMapper) {
        this.bookingRepository = bookingRepository;
        this.modelMapper = modelMapper;
    }

    @RabbitListener(queues = BookingRabbitMQConfig.QUEUE)
    public void receiveMessage(FlightBookingData bookingData) {
        // Process booking data
        processBooking(bookingData);
    }

    private void processBooking(FlightBookingData bookingData) {
        System.out.println("booking data received: " + bookingData.getTravelersData().get(0).getFirstName());
        // Booking logic here
        BookingDataEntity bookingDataEntity = modelMapper.map(bookingData, BookingDataEntity.class);
        bookingDataEntity.setStatus(BookingStatus.UNCONFIRMED);
        bookingRepository.save(bookingDataEntity);
        sendMessage(bookingDataEntity);

    }

    @RabbitListener(queues = PaymentRabbitMQConfig.PAYMENT_QUEUE)
    public void sendMessage(BookingDataEntity bookingData) {
        rabbitMQSender.sendMessage(PaymentRabbitMQConfig.PAYMENT_EXCHANGE, "payment", bookingData);
    }

}
