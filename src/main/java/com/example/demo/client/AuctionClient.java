package com.example.demo.client;

import com.example.demo.dto.AuctionDTO;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "auctionClient", url = "http://" + "${host}" + ":${port}")
public interface AuctionClient {

    @RequestMapping(method = RequestMethod.POST, path = "auction")
    @Headers("Content-Type: application/json")
    AuctionDTO createAuction(@RequestBody AuctionDTO auctionDTO);
}
