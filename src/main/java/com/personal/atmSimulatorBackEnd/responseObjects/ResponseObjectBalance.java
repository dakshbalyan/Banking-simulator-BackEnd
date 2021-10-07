package com.personal.atmSimulatorBackEnd.responseObjects;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseObjectBalance {

    private int remainingBalance;

    private String status;
    private String message;
    private String exception;

}
