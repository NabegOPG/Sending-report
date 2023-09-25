package com.javastart.hellospring.controller;

import com.javastart.hellospring.controller.dto.AccountRequestDTO;
import com.javastart.hellospring.controller.dto.AccountResponceDTO;
import com.javastart.hellospring.entity.Account;
import com.javastart.hellospring.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class AccountController {

    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/hello")
    public String helloSpring() {
        return "Hello Spring!";
    }
    @PostMapping("/accounts")
    public Long createAccount(@RequestBody AccountRequestDTO accountRequestDTO){
        return accountService.createAccount(accountRequestDTO.getName(),
                accountRequestDTO.getEmail(),
                accountRequestDTO.getBill());
    }

    @GetMapping("/accounts/{id}")
    public AccountResponceDTO getAccount(@PathVariable Long id){
        return new AccountResponceDTO(accountService.getAccountById(id));
    }

    @GetMapping("accounts")
    public List<AccountResponceDTO> getAll() {
        return accountService.getAll().stream()
                .map(AccountResponceDTO::new)
                .collect(Collectors.toList());
    }

    @DeleteMapping("/accounts/{id}")
    public AccountResponceDTO delete(@PathVariable Long id) {
        return new AccountResponceDTO(accountService.deleteById(id));
    }
}
