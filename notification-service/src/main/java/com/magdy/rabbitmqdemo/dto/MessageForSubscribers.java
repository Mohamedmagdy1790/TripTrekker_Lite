package com.magdy.rabbitmqdemo.dto;

import com.magdy.rabbitmqdemo.enums.EventType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
 public class MessageForSubscribers {

    String message;

    EventType eventtype;
}
