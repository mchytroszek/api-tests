package com.example.demo;

import com.example.demo.service.AuctionService;
import com.example.demo.service.OrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FeignClientTest {

    @Autowired
    private
    OrderService orderService;
    @Autowired
    private
    AuctionService auctionService;

    @Test
    public void placeAnOrder() {
        //given
        Integer auctionId = auctionService.createAuction();

        //when
        Integer orderId = orderService.placeAnOrder(auctionId);

        //then
        orderService.assertThatOrderExists(orderId);
    }

}
