package com.personal.atmSimulatorBackEnd.serviceClasses.createService;

import com.personal.atmSimulatorBackEnd.customExceptions.InvalidInputException;
import com.personal.atmSimulatorBackEnd.entities.Bank;
import com.personal.atmSimulatorBackEnd.repositories.BankRepository;
import com.personal.atmSimulatorBackEnd.requestObjects.RequestObjectRegisterBank;
import com.personal.atmSimulatorBackEnd.responseObjects.ResponseObjectStatus;
import com.personal.atmSimulatorBackEnd.serviceClasses.CreateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CreateBankService implements CreateService<Bank> {
    private final Logger LOGGER = LoggerFactory.getLogger(CreateBankService.class);
    @Autowired
    private BankRepository bankRepository;

    @Transactional
    public ResponseObjectStatus create(RequestObjectRegisterBank registerBank){
        Bank bank = Bank.builder().build();
        ResponseObjectStatus response;

        try{
            if (registerBank == null)
                throw new InvalidInputException("Entered Bank details not correct !");

            if(bankRepository.findByBankNameAndBranchName (
                    registerBank.getBankName(), registerBank.getBranchName()) != null){
                bank = bankRepository.findByBankNameAndBranchName(
                        registerBank.getBankName(), registerBank.getBranchName());
                response = ResponseObjectStatus.builder()
                        .status("200")
                        .message("Bank already exists !")
                        .build();
                LOGGER.info("Bank fetched !!!");

            }else {
                bank.setBankName(registerBank.getBankName());
                bank.setBranchName(registerBank.getBranchName());
                bankRepository.save(bank);
                response = ResponseObjectStatus.builder()
                        .status("200")
                        .message("New bank registered !")
                        .build();
                LOGGER.info("New bank registered !!!");
            }
        } catch (InvalidInputException e) {
            response = ResponseObjectStatus.builder()
                    .status("400")
                    .exception(e.toString())
                    .build();
            e.printStackTrace();
        } catch(Exception e){
            response = ResponseObjectStatus.builder()
                    .status("400")
                    .exception(e.toString())
                    .build();
            e.printStackTrace();
        }

        return response;
    }


    public Bank getBankByNameAndBranch(RequestObjectRegisterBank request) throws InvalidInputException {
        Bank bank = bankRepository.findByBankNameAndBranchName(request.getBankName(), request.getBranchName());
        if(bank == null)
            throw new InvalidInputException("Bank does not exist !");

        return bank;
    }
}
