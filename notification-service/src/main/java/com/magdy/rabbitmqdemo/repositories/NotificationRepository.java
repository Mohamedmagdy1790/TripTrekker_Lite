package com.magdy.rabbitmqdemo.repositories;


import com.magdy.rabbitmqdemo.entities.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification,Long> {
}
