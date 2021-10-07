package com.personal.atmSimulatorBackEnd.responseObjects;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseObjectGetAccountDetails {
    private int accountID;
    private String userName;
    private String bankName;
    private String branchName;
    private String date;

    private String status;
    private String message;
    private String exception;
}
