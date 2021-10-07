package com.personal.atmSimulatorBackEnd.repositories;

import com.personal.atmSimulatorBackEnd.entities.Account;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

public interface AccountRepository extends CrudRepository<Account, Integer> {

    public Account findByAccountIndex(int accountID);

    public Account findByAccountID(int accountId);

    @Modifying
    @Transactional
    @Query(value = "delete from account a where a.id = ?1", nativeQuery = true)
    void deleteAccountByAccountId(int accountID);
}
