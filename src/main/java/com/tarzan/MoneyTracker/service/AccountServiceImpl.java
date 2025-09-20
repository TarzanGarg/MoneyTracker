package com.tarzan.MoneyTracker.service;

import com.tarzan.MoneyTracker.entity.Account;
import com.tarzan.MoneyTracker.repository.AccountRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService{

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public List<Account> fetchAccounts() {
        return accountRepository.findAll();
    }

    @Override
    public Account createAccount(Account account) {
        return accountRepository.save(account);
    }

    @Override
    public Optional<Account> fetchAccountById(Long id) {
        return accountRepository.findById(id);
    }

    @Override
    public boolean deleteByIdIfExists(Long id) {
        if (accountRepository.existsById(id)) {
            accountRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Account updateAccount(long id, Account account) {
        Account accountDb= accountRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No account found"));

        if(Objects.nonNull(account.getAccountName()) && !"".equalsIgnoreCase(account.getAccountName())){
            accountDb.setAccountName(account.getAccountName());
        }

        if(Objects.nonNull(account.getAccountBalance())){
            accountDb.setAccountBalance(account.getAccountBalance());
        }

        accountRepository.save(accountDb);
        return accountDb;
    }
}
