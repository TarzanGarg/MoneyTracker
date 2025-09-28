package com.tarzan.MoneyTracker.controller;

import com.tarzan.MoneyTracker.entity.Account;
import com.tarzan.MoneyTracker.service.AccountService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class AccountController {
    @Autowired
    private AccountService accountService;

    private final Logger LOGGER = LoggerFactory.getLogger(AccountController.class);

    @PostMapping("/accounts")

    public Account createAccount(@Valid @RequestBody Account account) {
        LOGGER.info("Inside save createAccount of AccountController {}", account);
        return accountService.createAccount(account);
    }

    @GetMapping("/accounts")
    public List<Account> fetchAccounts() {
        LOGGER.info("Inside save fetchAccountsList of AccountController");
        return accountService.fetchAccounts();
    }

    @GetMapping("accounts/{id}")
    public Account fetchAccountById(@PathVariable("id") Long id) {
        return accountService.fetchAccountById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Account with id " + id + " not found"));
    }

    @DeleteMapping("accounts/{id}")
    public String deleteById(@PathVariable("id") Long id) {
        var exists = accountService.deleteByIdIfExists(id);
        if (exists) {
            return "Account deleted successfully";
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Account with id " + id + " not found");
        }
    }

    @PutMapping("accounts/{id}")
    public Account updateAccount(@PathVariable("id") Long id, @RequestBody Account account) {
        return accountService.updateAccount(id, account)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Account with id " + id + " not found"));
    }

    @GetMapping("accounts/name/{name}")
    public Account fetchDataByName(@PathVariable("name") String accountName) {
        return accountService.fetchAccountByName(accountName);
    }
}
