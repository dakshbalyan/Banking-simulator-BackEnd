package com.personal.atmSimulatorBackEnd.responseObjects;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseObjectGetWithdrawDetails {

    private int debitedAmount;
    private int totalAmount;

    private String status;
    private String message;
    private String exception;


}
