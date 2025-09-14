package com.tarzan.MoneyTracker.service;


import com.tarzan.MoneyTracker.entity.Account;

import java.util.List;
import java.util.Optional;

public interface AccountService {

    List<Account> fetchAccounts();

    Account createAccount(Account account);

    Optional<Account> fetchAccountById(Long id);

    boolean deleteByIdIfExists(Long id);
}
