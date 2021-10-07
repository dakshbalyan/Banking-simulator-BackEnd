package com.personal.atmSimulatorBackEnd.serviceClasses.readService;

import com.personal.atmSimulatorBackEnd.customExceptions.AccountDoesNotExistException;
import com.personal.atmSimulatorBackEnd.entities.Account;
import com.personal.atmSimulatorBackEnd.repositories.AccountRepository;
import com.personal.atmSimulatorBackEnd.requestObjects.RequestObjectWithAccountId;
import com.personal.atmSimulatorBackEnd.responseObjects.ResponseObjectGetAccountDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class GetAccountDetailsService {
    private final Logger LOGGER = LoggerFactory.getLogger(GetAccountDetailsService.class);


    @Autowired
    private AccountRepository accountRepository;

    public ResponseObjectGetAccountDetails getAccountDetails(
            RequestObjectWithAccountId requestAccountDetails
    ){
        ResponseObjectGetAccountDetails response;
        try{
            Account account = accountRepository.findByAccountID(
                    requestAccountDetails.getAccountId());

            if(account == null)
                throw new AccountDoesNotExistException("Account does not exist !");

            response = ResponseObjectGetAccountDetails.builder()
                    .accountID(account.getAccountID())
                    .bankName(account.getBank().getBankName())
                    .branchName(account.getBank().getBranchName())
                    .date(new Date().toString())
                    .userName(account.getUser().getUserName())
                    .status("200")
                    .message("Fetched account details successfully !")
                    .build();

            LOGGER.info("Fetched account details successfully !");

        } catch (AccountDoesNotExistException e) {
            response = ResponseObjectGetAccountDetails.builder()
                    .status("400")
                    .exception(e.toString())
                    .build();
            e.printStackTrace();
        } catch (Exception e) {
            response = ResponseObjectGetAccountDetails.builder()
                    .status("500")
                    .exception(e.toString())
                    .build();
            e.printStackTrace();
        }

        return response;
    }
}
