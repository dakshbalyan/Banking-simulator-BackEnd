package com.personal.atmSimulatorBackEnd.repositories;

import com.personal.atmSimulatorBackEnd.entities.LoginDetails;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface LoginRepository extends CrudRepository<LoginDetails, Integer> {
    @Query(value = "select * from login_details ld where ld.account_id = :accountID", nativeQuery = true)
    public LoginDetails getLoginByAccountId(@Param("accountID") int accountID);

    public LoginDetails findByAccount(int accountId);

    @Query(value = "select ld.pin from login_details ld where ld.account_id = ?1", nativeQuery = true)
    public Integer getLoginPinByAccountId(int accountID);

    @Modifying
    @Transactional
    @Query(value = "delete from login_details ld where ld.account_id = ?1", nativeQuery = true)
    void deleteAccountByAccountIndex(int accountIndex);
}
