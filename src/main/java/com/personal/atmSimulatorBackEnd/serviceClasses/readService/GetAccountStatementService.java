package com.personal.atmSimulatorBackEnd.serviceClasses.readService;

import com.personal.atmSimulatorBackEnd.customExceptions.AccountDoesNotExistException;
import com.personal.atmSimulatorBackEnd.entities.Account;
import com.personal.atmSimulatorBackEnd.entities.Transaction;
import com.personal.atmSimulatorBackEnd.repositories.AccountRepository;
import com.personal.atmSimulatorBackEnd.repositories.TransactionRepository;
import com.personal.atmSimulatorBackEnd.requestObjects.RequestObjectWithAccountId;
import com.personal.atmSimulatorBackEnd.responseObjects.ResponseObjectGetTransactionStatement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetAccountStatementService {
    private final Logger LOGGER = LoggerFactory.getLogger(GetAccountStatementService.class);

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    public ResponseObjectGetTransactionStatement getTransactionStatement(
            RequestObjectWithAccountId requestTransactionStatement
    ){
        ResponseObjectGetTransactionStatement response;
        LOGGER.warn(String.valueOf(requestTransactionStatement));
        try{
            Account account = accountRepository.findByAccountID(
                    requestTransactionStatement.getAccountId());
            if(account == null)
                throw new AccountDoesNotExistException("Account does not exist !");

            List<Transaction> statement = transactionRepository.findByAccountIndex(
                    account.getAccountIndex());
            response = ResponseObjectGetTransactionStatement.builder()
                    .accountID(account.getAccountID())
                    .bankName(account.getBank().getBankName())
                    .branchName(account.getBank().getBranchName())
                    .userName(account.getUser().getUserName())
                    .statement(statement)
                    .status("200")
                    .build();


            if(statement.size() != 0) {
//                response.setStatement(statement);
                response.setMessage("Transaction List fetched successfully !");
                LOGGER.info("Transaction List fetched successfully !");
            }
            else {
//                response.setStatement(new );
                response.setMessage("No transactions have been yet made !");
                LOGGER.info("No transactions have been yet made !");
            }
        } catch (AccountDoesNotExistException e) {
            response = ResponseObjectGetTransactionStatement.builder()
                        .status("400")
                        .exception(e.toString())
                        .build();
            e.printStackTrace();
        } catch (Exception e){
            response = ResponseObjectGetTransactionStatement.builder()
                    .status("500")
                    .exception(e.toString())
                    .build();
            e.printStackTrace();
        }

        return response;
    }
}

