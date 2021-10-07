package com.personal.atmSimulatorBackEnd.repositories;

import com.personal.atmSimulatorBackEnd.entities.Account;
import com.personal.atmSimulatorBackEnd.entities.Transaction;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface TransactionRepository extends CrudRepository<Transaction, Integer> {
    @Modifying
    @Transactional
    @Query(value = "delete from transaction t where t.account_id = ?1", nativeQuery = true)
    void deleteTransactionsOfAccountByAccountIndex(int accountIndex);

    @Query(value = "select * from transaction where account_id = ?1", nativeQuery = true)
    public List<Transaction> findByAccountIndex(int accountIndex);
}
