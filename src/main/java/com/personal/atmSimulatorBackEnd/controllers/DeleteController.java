package com.personal.atmSimulatorBackEnd.controllers;

import com.personal.atmSimulatorBackEnd.requestObjects.RequestObjectWithAccountId;
import com.personal.atmSimulatorBackEnd.responseObjects.ResponseObjectGetDeleteAccount;
import com.personal.atmSimulatorBackEnd.serviceClasses.deleteService.DeleteAccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class DeleteController {
    private final Logger LOGGER = LoggerFactory.getLogger(DeleteController.class);

    @Autowired
    private DeleteAccountService deleteAccountService;

    @DeleteMapping("/home/account/deleteAccount")
    public ResponseObjectGetDeleteAccount deleteAccount(
            @RequestBody RequestObjectWithAccountId requestDeleteAccount
    ) {
        LOGGER.warn(requestDeleteAccount.toString());
        return deleteAccountService.getDeleteAccount(requestDeleteAccount);
    }
}
