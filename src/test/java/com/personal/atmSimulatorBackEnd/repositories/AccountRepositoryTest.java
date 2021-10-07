package com.personal.atmSimulatorBackEnd.repositories;

import com.personal.atmSimulatorBackEnd.entities.Account;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AccountRepositoryTest {

    @Autowired
    private AccountRepository accountRepository;

    @Test
    void findByAccountID() {
        Account account = accountRepository.findByAccountID(91359);
        System.out.println(account);
    }
}