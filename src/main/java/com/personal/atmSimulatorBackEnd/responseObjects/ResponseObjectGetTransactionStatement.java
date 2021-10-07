package com.personal.atmSimulatorBackEnd.responseObjects;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.personal.atmSimulatorBackEnd.entities.Transaction;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseObjectGetTransactionStatement {
	
	private int accountID;
    private String bankName;
    private String branchName;
    private String userName;
    List<Transaction> statement ;

    private String status;
    private String message;
    private String exception;
}
