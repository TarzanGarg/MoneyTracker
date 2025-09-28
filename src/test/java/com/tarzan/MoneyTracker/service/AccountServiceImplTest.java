package com.tarzan.MoneyTracker.service;

import com.tarzan.MoneyTracker.entity.Account;
import com.tarzan.MoneyTracker.repository.AccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.util.Optional;

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
                .id(989L)
                .accountName("cash")
                .accountBalance(new BigDecimal(5674))
                .build();
        Mockito.when(accountRepository.findByAccountNameIgnoreCase("cash"))
                .thenReturn(account);
        Mockito.when(accountRepository.findById(989L))
                .thenReturn(Optional.ofNullable(account));
    }

    @Test
    @DisplayName("Get data based on valid account name")
    public void WhenValidAccountName_thenAccountShouldFound() {
        String accountName = "cash";
        Account found = accountService.fetchAccountByName(accountName);
        assertEquals(accountName, found.getAccountName());
    }


    @Test
    @DisplayName("Get data based on valid account Id")
    public void WhenValidAccountId_thenAccountShouldFound() {
        Long Id = 989L;
        Optional<Account> found = accountService.fetchAccountById(Id);
        assertEquals(Id, found.get().getId());
    }
}
