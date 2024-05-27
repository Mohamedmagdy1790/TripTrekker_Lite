package com.magdy.rabbitmqdemo.services;

import com.magdy.rabbitmqdemo.dto.NotificationMessage;
import com.magdy.rabbitmqdemo.entities.Booking;
import com.magdy.rabbitmqdemo.entities.Notification;
import com.magdy.rabbitmqdemo.enums.NotificationStatus;
import com.magdy.rabbitmqdemo.repositories.BookingRepository;
import com.magdy.rabbitmqdemo.repositories.NotificationRepository;
import jakarta.mail.MessagingException;
import org.springframework.stereotype.Service;


@Service
public class NotificationService {
     final MailSenderService mailService;
     NotificationRepository notificationRepository;
    BookingRepository bookingRepository;



    public NotificationService(MailSenderService mailService, NotificationRepository notificationRepository) {
        this.mailService = mailService;
        this.notificationRepository = notificationRepository;
    }


    public  void sendEmail(NotificationMessage notificationMessage )throws MessagingException {


//               //get notification data from database using notification_id and booking_id
//               Notification notification = notificationRepository.findById((long) notificationMessage.getNotificationId()).orElseThrow (
//                       () -> new RuntimeException("Notification not found");
//               );
//               Booking booking= bookingRepository.findById(notificationMessage.getBookingId()).get();


        // Send (email) notification to customer
        Notification notification=new Notification ();
        Booking booking=new Booking ();
        mailService.sendHtmlMail("mmagdy3396@gmail.com",notification,booking);

        // update notification status from (not sent) --> to (sent)
        // updateNotificationStatusInDatabase(notification);
    }


    public  void sendEmailForSubscribers(String email, String message )throws MessagingException {


        mailService.sendHtmlMailForDiscount(email,message);

        // update notification status from (not sent) --> to (sent)
        // updateNotificationStatusInDatabase(notification);
    }

    private void updateNotificationStatusInDatabase(Notification notification) {
        notification.setStatus(NotificationStatus.SENT);
        notificationRepository.save(notification);

    }


}
