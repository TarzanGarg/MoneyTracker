package com.tarzan.MoneyTracker.controller;

import com.tarzan.MoneyTracker.entity.Account;
import com.tarzan.MoneyTracker.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class AccountController {
    @Autowired
    private AccountService accountService;

    @PostMapping("/accounts")
    public Account createAccount(@RequestBody Account account) {
        return accountService.createAccount(account);
    }

    @GetMapping("/accounts")
    public List<Account> fetchAccounts() {
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

}
