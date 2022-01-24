package com.learning.webflexdemo.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Data
@ToString
@Setter
@Getter
public class InputFailedValidationResponse {
    private int errorCode;
    private int input;
    private String message;

}
