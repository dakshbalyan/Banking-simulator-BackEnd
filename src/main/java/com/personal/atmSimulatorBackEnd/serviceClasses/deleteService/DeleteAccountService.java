package com.personal.atmSimulatorBackEnd.serviceClasses.deleteService;

import com.personal.atmSimulatorBackEnd.customExceptions.AccountDoesNotExistException;
import com.personal.atmSimulatorBackEnd.entities.Account;
import com.personal.atmSimulatorBackEnd.repositories.AccountRepository;
import com.personal.atmSimulatorBackEnd.repositories.LoginRepository;
import com.personal.atmSimulatorBackEnd.repositories.TransactionRepository;
import com.personal.atmSimulatorBackEnd.requestObjects.RequestObjectWithAccountId;
import com.personal.atmSimulatorBackEnd.responseObjects.ResponseObjectGetDeleteAccount;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class DeleteAccountService {
    private final Logger LOGGER = LoggerFactory.getLogger(DeleteAccountService.class);

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private LoginRepository loginRepository;
    @Autowired
    private TransactionRepository transactionRepository;

    @Transactional
    public ResponseObjectGetDeleteAccount getDeleteAccount(
            RequestObjectWithAccountId requestDeleteAccount
    ){
        ResponseObjectGetDeleteAccount response;
        try{
            response = ResponseObjectGetDeleteAccount.builder().build();
            Account account = accountRepository.findByAccountID(requestDeleteAccount.getAccountId());
            if(account == null) {
                throw new AccountDoesNotExistException("Account does not Exist !!!");
            }

            loginRepository.deleteAccountByAccountIndex(account.getAccountIndex());
            LOGGER.info("Entry deleted from login tbl !!!");
            transactionRepository.deleteTransactionsOfAccountByAccountIndex(account.getAccountIndex());
            LOGGER.info("Entries deleted from transaction tbl !!!");
            accountRepository.deleteAccountByAccountId(account.getAccountID());
            LOGGER.info("Entry deleted from account tbl !!!");

            response.setAccountID(account.getAccountID());
            response.setBankName(account.getBank().getBankName());
            response.setBranchName(account.getBank().getBranchName());
            response.setDate(new Date().toString());
            response.setStatus("200");
            response.setMessage("Account deleted successfully !");
        } catch (AccountDoesNotExistException e) {
            response = ResponseObjectGetDeleteAccount.builder().build();
            response.setStatus("400");
            response.setException(e.toString());
            e.printStackTrace();
        } catch (Exception e) {
            response = ResponseObjectGetDeleteAccount.builder().build();
            response.setStatus("500");
            response.setException(e.toString());
            e.printStackTrace();
        }

        return response;
    }
}
