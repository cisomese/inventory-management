package com.francis.inventmgmt.dto;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderDto {
    private List<String> itemName;
    private String customerName;
    private List<Integer> quantity;
}
