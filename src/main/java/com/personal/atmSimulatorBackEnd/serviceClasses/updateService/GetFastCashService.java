package com.personal.atmSimulatorBackEnd.serviceClasses.updateService;

import com.personal.atmSimulatorBackEnd.customExceptions.AccountDoesNotExistException;
import com.personal.atmSimulatorBackEnd.customExceptions.InvalidInputException;
import com.personal.atmSimulatorBackEnd.entities.Account;
import com.personal.atmSimulatorBackEnd.entities.Transaction;
import com.personal.atmSimulatorBackEnd.repositories.AccountRepository;
import com.personal.atmSimulatorBackEnd.repositories.TransactionRepository;
import com.personal.atmSimulatorBackEnd.requestObjects.RequestObjectTransactionAmount;
import com.personal.atmSimulatorBackEnd.responseObjects.ResponseObjectGetWithdrawDetails;
import com.personal.atmSimulatorBackEnd.utilityClasses.ValidateEnums;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class GetFastCashService {
    private final Logger LOGGER = LoggerFactory.getLogger(GetFastCashService.class);

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionRepository transactionRepository;
    @Transactional
    public ResponseObjectGetWithdrawDetails withdrawFastCash(
            RequestObjectTransactionAmount requestFastCash
    ){
        ResponseObjectGetWithdrawDetails response;
        try{
            response = ResponseObjectGetWithdrawDetails.builder().build();
            int cashAmount = requestFastCash.getTransactionAmount();
            if(!ValidateEnums.isFastCashAmountValid(cashAmount))
                throw new InvalidInputException("Selected fast cash option not available !!!");

            Account account = accountRepository.findByAccountID(requestFastCash.getAccountId());
            if(account == null)
                throw new AccountDoesNotExistException("Account does not exist !");
            Transaction transaction;
            int updatedBalance;
            if (cashAmount <= account.getAmount()) {
                updatedBalance = account.getAmount() - cashAmount;
                transaction = Transaction.builder()
                        .account(account)
                        .transactionDate(new Date())
                        .remainingAmt(updatedBalance)
                        .debitAmt(cashAmount)
                        .build();
            }
            else{
                throw new InvalidInputException("Withdrawal amount greater than balance !");
            }

            account.setAmount(updatedBalance);

            transactionRepository.save(transaction);
            LOGGER.info("Added transaction in tbl transaction!!");
            accountRepository.save(account);
            LOGGER.info("Updated balance in tbl Account!!");

            response.setDebitedAmount(cashAmount);
            response.setTotalAmount(updatedBalance);
            response.setStatus("200");
            response.setMessage("Fast Cash Withdrawn successfully !");

        } catch (InvalidInputException e) {
            response = ResponseObjectGetWithdrawDetails.builder()
                    .status("400")
                    .exception(e.toString())
                    .build();
            e.printStackTrace();
        } catch (AccountDoesNotExistException e) {
            response = ResponseObjectGetWithdrawDetails.builder()
                    .status("400")
                    .exception(e.toString())
                    .build();
            e.printStackTrace();
        } catch (Exception e) {
            response = ResponseObjectGetWithdrawDetails.builder()
                    .status("500")
                    .exception(e.toString())
                    .build();
            e.printStackTrace();
        }

        return response;
    }
}
