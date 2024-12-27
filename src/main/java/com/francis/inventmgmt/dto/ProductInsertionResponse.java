package com.francis.inventmgmt.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ProductInsertionResponse {
    private boolean status;
    private String message;
}
