package com.magdy.rabbitmqdemo.entities;


import com.magdy.rabbitmqdemo.enums.NotificationStatus;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "test")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;


    @Column(name = "name")
    private String name;


    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private NotificationStatus status;

}
