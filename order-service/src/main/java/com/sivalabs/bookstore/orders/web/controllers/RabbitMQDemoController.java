package com.sivalabs.bookstore.orders.web.controllers;

import org.springframework.web.bind.annotation.RestController;

@RestController
class RabbitMQDemoController {

    //    private final RabbitTemplate rabbitTemplate;
    //    private final ApplicationProperties properties;
    //
    //    RabbitMQDemoController(RabbitTemplate rabbitTemplate, ApplicationProperties properties) {
    //        this.rabbitTemplate = rabbitTemplate;
    //        this.properties = properties;
    //    }
    //
    //    @PostMapping("/send")
    //    public void sendMessage(@RequestBody MyMessage message) {
    //        rabbitTemplate.convertAndSend(properties.orderEventsExchange(), message.routingKey(), message.payload());
    //    }
}

record MyPayload(String content) {}

record MyMessage(String routingKey, MyPayload payload) {}
