package com.personal.atmSimulatorBackEnd.serviceClasses.readService;

import com.personal.atmSimulatorBackEnd.customExceptions.AccountDoesNotExistException;
import com.personal.atmSimulatorBackEnd.entities.Account;
import com.personal.atmSimulatorBackEnd.repositories.AccountRepository;
import com.personal.atmSimulatorBackEnd.responseObjects.ResponseObjectBalance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GetBalanceService {
    private final Logger LOGGER = LoggerFactory.getLogger(GetBalanceService.class);

    @Autowired
    private AccountRepository accountRepository;

    public ResponseObjectBalance getAccountBalance(
            int accountId
    ){
        ResponseObjectBalance response;
        try{
            response = ResponseObjectBalance.builder().build();

//            LOGGER.error(String.valueOf(accountId));
            Account account = accountRepository.findByAccountID(accountId);
            if(account == null)
                throw new AccountDoesNotExistException("Account does not exist !");

            response.setRemainingBalance(account.getAmount());
            response.setStatus("200");
            response.setMessage("Balance fetched successfully!");

            LOGGER.info("Balance fetched successfully!");
        } catch (AccountDoesNotExistException e) {
            response = ResponseObjectBalance.builder()
                    .status("400")
                    .exception(e.toString())
                    .build();
            e.printStackTrace();
        } catch (Exception e){
            response = ResponseObjectBalance.builder()
                    .status("400")
                    .exception(e.toString())
                    .build();
            e.printStackTrace();
        }

        return response;
    }
}
