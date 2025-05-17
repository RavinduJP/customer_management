package com.example.customer_management.dto.response;

public class ResponseDto<T> {
    private boolean success;
    private String message;
    private T data;
}
