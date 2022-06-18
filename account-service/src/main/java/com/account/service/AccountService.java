package com.account.service;

import com.account.entity.Account;
import com.account.exception.AccountNotFountException;
import com.account.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;

@Service
public class AccountService {
    private final AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account getAccountById(Long id) {
        return accountRepository.findById(id)
                .orElseThrow(() -> new AccountNotFountException("Account with id " + id + " not found"));
    }

    public Long createAccount(String name, String email, String phone, List<Long> bills) {
        Account account = new Account(name, email, phone, OffsetDateTime.now(), bills);
        return accountRepository.save(account).getAccountId();
    }

    public Account updateAccount(Long id, String name, String email, String phone, List<Long> bills) {
        Account account = new Account();
        account.setAccountId(id);
        account.setBills(bills);
        account.setEmail(email);
        account.setPhone(phone);
        account.setName(name);
        return accountRepository.save(account);
    }

    public Account deleteAccount(Long id) {
        Account deletedAccount = getAccountById(id);
        accountRepository.deleteById(id);
        return deletedAccount;
    }
}
