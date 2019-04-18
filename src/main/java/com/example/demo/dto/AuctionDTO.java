package com.example.demo.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AuctionDTO {
    private Integer id;
    private String name;
    private String description;
    private float price;
}
