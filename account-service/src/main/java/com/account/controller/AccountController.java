package com.account.controller;

import com.account.dto.AccountRequestDto;
import com.account.dto.AccountResponseDto;
import com.account.entity.Account;
import com.account.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/{id}")
    public AccountResponseDto getAccount(@PathVariable Long id) {
        return new AccountResponseDto(accountService.getAccountById(id));
    }

    @PostMapping("/")
    public Long createAccount(@RequestBody AccountRequestDto accountRequestDto) {
        return accountService.createAccount(accountRequestDto.getName(), accountRequestDto.getEmail(),
                accountRequestDto.getPhone(), accountRequestDto.getBills());
    }

    @PutMapping("/{id}")
    public AccountResponseDto updateAccount(@PathVariable Long id, @RequestBody AccountRequestDto accountRequestDto) {
        return new AccountResponseDto(accountService.updateAccount(id, accountRequestDto.getName(), accountRequestDto.getEmail(),
                accountRequestDto.getPhone(), accountRequestDto.getBills()));
    }

    @DeleteMapping("/{id}")
    public AccountResponseDto deleteAccount(@PathVariable Long id) {
        return new AccountResponseDto(accountService.deleteAccount(id));
    }
}
