package com.microservice.productsservice.Exceptions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
@Builder
public class ErrorMessage {
    private int errorCode;
    private String messages;
    private Date timestamp;
}
