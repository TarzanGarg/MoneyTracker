package com.tarzan.MoneyTracker.service;

import com.tarzan.MoneyTracker.entity.Account;
import com.tarzan.MoneyTracker.repository.AccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AccountServiceImplTest {

    @Autowired
    private AccountService accountService;
    @MockBean
    private AccountRepository accountRepository;

    @BeforeEach
    void setUp() {
        Account account = Account.builder()
                .accountName("cash")
                .accountBalance(new BigDecimal(5674))
                .build();
        Mockito.when(accountRepository.findByAccountNameIgnoreCase("cash"))
                .thenReturn(account);
    }

    @Test
    public void WhenValidAccountName_thenAccountShouldFound(){
        String accountName = "cash";
        Account found = accountService.fetchAccountByName(accountName);
        assertEquals(accountName, found.getAccountName());
    }
}