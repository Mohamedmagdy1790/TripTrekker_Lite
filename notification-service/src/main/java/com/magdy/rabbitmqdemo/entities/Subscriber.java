package com.magdy.rabbitmqdemo.entities;


import com.magdy.rabbitmqdemo.enums.EventType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "subscriber")
@Getter
@Setter
public class Subscriber {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    Integer id;
    String name;

    String email;

    @Enumerated(EnumType.STRING)
    EventType eventType;



}
