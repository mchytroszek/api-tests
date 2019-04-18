package com.example.demo.client;

import com.example.demo.dto.OrderDTO;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(name = "orderClient", url = "http://" + "${host}" + ":${port}")
public interface OrderClient {

    @RequestMapping(method = RequestMethod.POST, path = "order")
    @Headers("Content-Type: application/json")
    OrderDTO placeOrder(@RequestBody OrderDTO orderDTO);

    @RequestMapping(method = RequestMethod.GET, path = "order/{id}")
    @Headers("Content-Type: application/json")
    OrderDTO getOrder(@RequestParam Integer orderId);
}















