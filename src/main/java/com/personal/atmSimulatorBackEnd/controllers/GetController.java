package com.personal.atmSimulatorBackEnd.controllers;

// Functionalities included -> Read Operations
/*
        1. Account Details
        2. Account Statement
        3. Balance
 */

import com.personal.atmSimulatorBackEnd.requestObjects.RequestObjectWithAccountId;
import com.personal.atmSimulatorBackEnd.responseObjects.ResponseObjectBalance;
import com.personal.atmSimulatorBackEnd.responseObjects.ResponseObjectGetAccountDetails;
import com.personal.atmSimulatorBackEnd.responseObjects.ResponseObjectGetTransactionStatement;
import com.personal.atmSimulatorBackEnd.serviceClasses.readService.GetAccountDetailsService;
import com.personal.atmSimulatorBackEnd.serviceClasses.readService.GetAccountStatementService;
import com.personal.atmSimulatorBackEnd.serviceClasses.readService.GetBalanceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/home/account")
public class GetController {
    private final Logger LOGGER = LoggerFactory.getLogger(GetController.class);

    @Autowired
    private GetAccountDetailsService getAccountDetailsService;
    @Autowired
    private GetBalanceService getBalanceService;
    @Autowired
    private GetAccountStatementService getAccountStatementService;

    @GetMapping("/")
    public ResponseObjectGetAccountDetails getAccountDetails(
            @RequestBody RequestObjectWithAccountId requestAccountDetails
    ) {
        return getAccountDetailsService.getAccountDetails(requestAccountDetails);
    }

    @GetMapping("/balance")
    public ResponseObjectBalance responseBalance(
            @RequestBody RequestObjectWithAccountId requestBalance
    ) {
        LOGGER.warn(String.valueOf(requestBalance));
//        LOGGER.warn(String.valueOf(requestBalance.getAccountId()));
        return getBalanceService.getAccountBalance(requestBalance.getAccountId());
    }


    @GetMapping("/statement")
    public ResponseObjectGetTransactionStatement getAccountTransactionStatement(
            @RequestBody RequestObjectWithAccountId requestStatement
    ) {

        return getAccountStatementService.getTransactionStatement(requestStatement);
    }
}
