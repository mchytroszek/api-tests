package com.example.demo.service;

import com.example.demo.client.AuctionClient;
import com.example.demo.dto.AuctionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AuctionService {
    @Autowired
    private
    AuctionClient auctionClient;

    public Integer createAuction() {
        AuctionDTO auctionDTO = AuctionDTO.builder()
                .name("test name")
                .price(100)
                .description("test description")
                .build();

        return auctionClient.createAuction(auctionDTO).getId();
    }
}
