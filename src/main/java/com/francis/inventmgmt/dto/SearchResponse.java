package com.francis.inventmgmt.dto;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class SearchResponse {
    private String productName;
    private String message;
    private double price;
    
}
