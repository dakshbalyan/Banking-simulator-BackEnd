package com.personal.atmSimulatorBackEnd.controllers;
// Functionalities included -> Create Operations
/*
        1. Save Bank
        2. Create Account
 */

import com.personal.atmSimulatorBackEnd.customExceptions.InvalidInputException;
import com.personal.atmSimulatorBackEnd.entities.Bank;
import com.personal.atmSimulatorBackEnd.entities.User;
import com.personal.atmSimulatorBackEnd.requestObjects.RequestObjectCreateAccount;
import com.personal.atmSimulatorBackEnd.requestObjects.RequestObjectRegisterBank;
import com.personal.atmSimulatorBackEnd.requestObjects.RequestObjectRegisterUser;
import com.personal.atmSimulatorBackEnd.responseObjects.ResponseObjectCreateEntity;
import com.personal.atmSimulatorBackEnd.responseObjects.ResponseObjectStatus;
import com.personal.atmSimulatorBackEnd.serviceClasses.createService.CreateAccountService;
import com.personal.atmSimulatorBackEnd.serviceClasses.createService.CreateBankService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class PutController {
    private final Logger LOGGER = LoggerFactory.getLogger(PutController.class);

    @Autowired
    private CreateBankService createBankService;
    @Autowired
    private CreateAccountService createAccountService;

    @PutMapping("/home/createAccount")
    public ResponseObjectCreateEntity createAccount(
            @Valid @RequestBody RequestObjectRegisterUser requestObjectRegisterUser) throws InvalidInputException {
        // Only the bank and user details will come in from the front end
        // of web app; Account will be created on its own

//        Creates or fetches user
        User user = createAccountService.create(requestObjectRegisterUser);
        Bank bank = createBankService.getBankByNameAndBranch(requestObjectRegisterUser.getRegisterBank());
        RequestObjectCreateAccount requestCreateAccount =
                RequestObjectCreateAccount.builder()
                        .bank(bank)
                        .user(user)
                        .build();

        LOGGER.info("Creating Account !!");
        LOGGER.warn(String.valueOf(requestCreateAccount.getBank()));
        LOGGER.warn(String.valueOf(requestCreateAccount.getUser()));
        return createAccountService.create(requestCreateAccount);
    }

    @PutMapping("/home/registerBank")
    public ResponseObjectStatus selectBank(
            @Valid @RequestBody RequestObjectRegisterBank registerBank
    ) {
        LOGGER.info("Saving bank !!");

        return createBankService.create(registerBank);
    }

}
