package com.tarzan.MoneyTracker.repository;

import com.tarzan.MoneyTracker.entity.Account;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class AccountRepositoryTest {
    @Autowired
    AccountRepository accountRepository;

    @Autowired
    TestEntityManager entityManager;

    private Account createdAccount;

    @BeforeEach
    void setUp() {
        Account account = Account.builder()
                .accountName("cash")
                .accountBalance(new BigDecimal(877))
                .build();

        createdAccount = entityManager.persist(account);
    }

    @Test
    public void whenFindById_thenReturnAccount() {
        long validId = createdAccount.getId();
        Account account = accountRepository.findById(validId).get();
        assertEquals("cash", account.getAccountName());
    }
}