package com.sivalabs.bookstore.orders.web.controllers;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
class RabbitMQLListener {

    @RabbitListener(queues = "${orders.new-orders-queue}")
    public void handleNewOrder(MyPayload payload) {
        System.out.println("New order : " + payload.content());
    }

    @RabbitListener(queues = "${orders.delivered-orders-queue}")
    public void handleDeliveredOrder(MyPayload payload) {
        System.out.println("Delivered order : " + payload.content());
    }
}
