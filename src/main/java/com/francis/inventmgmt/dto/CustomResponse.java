package com.francis.inventmgmt.dto;


import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class CustomResponse {
    private String status;  
    private String response;  
}