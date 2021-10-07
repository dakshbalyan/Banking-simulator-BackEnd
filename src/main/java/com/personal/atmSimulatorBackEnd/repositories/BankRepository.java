package com.personal.atmSimulatorBackEnd.repositories;

import com.personal.atmSimulatorBackEnd.entities.Bank;
import org.springframework.data.repository.CrudRepository;

public interface BankRepository extends CrudRepository<Bank, Integer> {

    Bank findByBankNameAndBranchName(String bankName, String branchName);
}
