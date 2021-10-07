package com.personal.atmSimulatorBackEnd.controllers;
// Functionalities included -> Update Operations
/*
        1. Change Pin
        2. Deposit
        3. Withdraw
            a. Any amount
            b. fast cash
        4. Authenticate
 */

import com.personal.atmSimulatorBackEnd.requestObjects.*;
import com.personal.atmSimulatorBackEnd.responseObjects.*;
import com.personal.atmSimulatorBackEnd.serviceClasses.readService.AuthenticateUserService;
import com.personal.atmSimulatorBackEnd.serviceClasses.updateService.ChangePinService;
import com.personal.atmSimulatorBackEnd.serviceClasses.updateService.DepositAmountService;
import com.personal.atmSimulatorBackEnd.serviceClasses.updateService.GetFastCashService;
import com.personal.atmSimulatorBackEnd.serviceClasses.updateService.WithdrawAmountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/home")
public class PostController {
    private final Logger LOGGER = LoggerFactory.getLogger(PostController.class);
    @Autowired
    private ChangePinService changePin;
    @Autowired
    private DepositAmountService depositAmount;
    @Autowired
    private WithdrawAmountService withdrawAmount;
    @Autowired
    private GetFastCashService getFastCash;
    @Autowired
    private AuthenticateUserService authenticateUserService;

    @PostMapping("/account/changePin")
    public ResponseObjectStatus responseChangePin(
            @RequestBody RequestObjectPinChange requestPinChange
    ) {

        LOGGER.warn(requestPinChange.toString());
        return changePin.changeAccountPin(requestPinChange);
    }

    @PostMapping("/account/deposit")
    public ResponseObjectGetDepositAmountDetails depositAmount(
            @RequestBody RequestObjectTransactionAmount requestDepositAmount
    ) {
        LOGGER.warn(requestDepositAmount.toString());
        return depositAmount.depsoitAmount(requestDepositAmount);
    }

    @PostMapping("/account/withdraw")
    public ResponseObjectGetWithdrawDetails withdrawAmount(
            @RequestBody RequestObjectTransactionAmount requestWithdrawAmount
    ) {
        LOGGER.warn(requestWithdrawAmount.toString());
        return withdrawAmount.getWithdrawAmountDetails(requestWithdrawAmount);
    }

    @PostMapping("/account/fastCash")
    public ResponseObjectGetWithdrawDetails responseFastCash(
            @RequestBody RequestObjectTransactionAmount requestFastCash
    ) {
        LOGGER.warn(requestFastCash.toString());
        return getFastCash.withdrawFastCash(requestFastCash);
    }

    @PostMapping("/login")
    public ResponseObjectStatus authenticateUser(
            @RequestBody RequestObjectLoginCredentials requestAuthentication) {
        return authenticateUserService.getAuthentication(requestAuthentication);

    }
}
