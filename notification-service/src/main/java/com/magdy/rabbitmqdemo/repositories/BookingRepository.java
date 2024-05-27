package com.magdy.rabbitmqdemo.repositories;

import com.magdy.rabbitmqdemo.entities.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Integer> {
}
