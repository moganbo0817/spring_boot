package com.example.api.exception;

import lombok.Value;

@Value
public class ErrorResponse {
    String errorCode;
    String message;
}