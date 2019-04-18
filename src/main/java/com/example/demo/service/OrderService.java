package com.example.demo.service;

import com.example.demo.client.OrderClient;
import com.example.demo.dto.OrderDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;

@Component
public class OrderService {
    @Autowired
    private
    OrderClient orderClient;

    public Integer placeAnOrder(Integer auctionId) {
        OrderDTO orderDTO = OrderDTO.builder()
                .auctionID(auctionId)
                .quantity(1)
                .build();

        return orderClient.placeOrder(orderDTO).getId();
    }

    public void assertThatOrderExists(Integer orderId) {
        assertThat(orderClient.getOrder(orderId), notNullValue());
    }
}


