package com.francis.inventmgmt.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder

public class UpdateProductResponse {
    private boolean status;
    private String response;
}
