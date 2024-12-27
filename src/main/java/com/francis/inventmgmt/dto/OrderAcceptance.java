package com.francis.inventmgmt.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderAcceptance {
    private String response;
    private boolean status;
    private double totalPrice;
}
