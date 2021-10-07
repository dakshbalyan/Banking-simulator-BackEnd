package com.personal.atmSimulatorBackEnd.serviceClasses.updateService;

import com.personal.atmSimulatorBackEnd.customExceptions.AccountDoesNotExistException;
import com.personal.atmSimulatorBackEnd.entities.Account;
import com.personal.atmSimulatorBackEnd.entities.Transaction;
import com.personal.atmSimulatorBackEnd.repositories.AccountRepository;
import com.personal.atmSimulatorBackEnd.repositories.TransactionRepository;
import com.personal.atmSimulatorBackEnd.requestObjects.RequestObjectTransactionAmount;
import com.personal.atmSimulatorBackEnd.responseObjects.ResponseObjectGetDepositAmountDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class DepositAmountService {
    private final Logger LOGGER = LoggerFactory.getLogger(DepositAmountService.class);

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Transactional
    public ResponseObjectGetDepositAmountDetails depsoitAmount(
            RequestObjectTransactionAmount requestDepositAmount
    ){
        ResponseObjectGetDepositAmountDetails response;
        try{
            Account account = accountRepository
                    .findByAccountID(requestDepositAmount.getAccountId());
            if(account == null)
                throw new AccountDoesNotExistException("Account does not exist !");

            int updatedBalance = account.getAmount() + requestDepositAmount.getTransactionAmount();
            Transaction transaction = Transaction.builder()
                    .account(account)
                    .transactionDate(new Date())
                    .remainingAmt(updatedBalance)
                    .creditAmt(requestDepositAmount.getTransactionAmount())
                    .build();

            account.setAmount(updatedBalance);

            transactionRepository.save(transaction);
            LOGGER.info("Added transaction in transaction tbl!!!");
            accountRepository.save(account);
            LOGGER.info("Updated balance in account tbl!!!");

            response = ResponseObjectGetDepositAmountDetails
                    .builder()
                    .creditedAmount(requestDepositAmount.getTransactionAmount())
                    .totalAmount(updatedBalance)
                    .status("200")
                    .message("Amount deposited successfully !")
                    .build();

        } catch (AccountDoesNotExistException e) {
            response = ResponseObjectGetDepositAmountDetails
                    .builder()
                    .status("400")
                    .exception(e.toString())
                    .build();

            e.printStackTrace();
        } catch (Exception e) {
            response = ResponseObjectGetDepositAmountDetails
                    .builder()
                    .status("500")
                    .exception(e.toString())
                    .build();
            e.printStackTrace();
        }

        return response;
    }
}
