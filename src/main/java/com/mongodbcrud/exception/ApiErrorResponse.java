package com.mongodbcrud.exception;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Data
@Builder
public class ApiErrorResponse {
    private Instant timestamp;
    private String category;
    private String message;
}

