package com.tarzan.MoneyTracker.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.math.BigDecimal;
@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long id;
    public BigDecimal accountBalance;
    public String accountName;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public BigDecimal getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(BigDecimal accountBalance) {
        this.accountBalance = accountBalance;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public Account() {

    }

    public Account(long id, BigDecimal accountBalance, String accountName) {
        this.id = id;
        this.accountBalance = accountBalance;
        this.accountName = accountName;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", accountBalance=" + accountBalance +
                ", AccountName='" + accountName + '\'' +
                '}';
    }
}
