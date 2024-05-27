package com.magdy.rabbitmqdemo.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;

@Configuration
public class MessageRabbitMQConfig {

    @Value("${rabbitmq.queue.name}")
    private String queue;

    @Value("${rabbitmq.queue.name.discount}")
    private String discount_queue;

    @Value("${rabbitmq.exchange.name}")
    private String exchange ;

    @Value("${rabbitmq.routing_key.name}")
    private String routingKey ;

    @Value("${rabbitmq.routing_key.name.discount}")
    private String discountRoutingKey ;


    // creating exchanges
    @Bean
    public TopicExchange Exchange(){
        return  new TopicExchange(exchange);

    }
    // creating queues
    @Bean
    public Queue queue(){
        return new Queue(queue);
    }


   // binding exchange to a spicfic queue usnig binding key

    @Bean
    public Queue discountQueue(){
        return new Queue(discount_queue);
    }


    // binding exchange to a spicfic queue usnig binding key
    @Bean
    public Binding binding(){
        return BindingBuilder.bind(queue()).to(Exchange()).with(routingKey);
    }
    @Bean
    public Binding discount_binding(){
        return BindingBuilder.bind(discountQueue()).to(Exchange()).with(discountRoutingKey);
    }


    @Bean
    public MessageConverter converter(){
        return  new Jackson2JsonMessageConverter();
    }

    //AMQP template is the interface where rabbit template is the class which implments this interface
    @Bean
    public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory){
        RabbitTemplate rabbitTemplate =new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(converter());

        return rabbitTemplate;


    }


     //we have also to configure 3 beans which are but spring boot auro configuration done this job automatically we will just injest them
    // ConnectionFactory
    //RabbitTEMPLATE
    //RabbitAdmin

}
