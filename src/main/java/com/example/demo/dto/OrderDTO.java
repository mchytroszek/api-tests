package com.example.demo.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class OrderDTO {
    private Integer id;
    private Integer auctionID;
    private Integer quantity;
}
