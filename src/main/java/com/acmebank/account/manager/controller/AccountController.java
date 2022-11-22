package com.acmebank.account.manager.controller;

import com.acmebank.account.manager.dto.AccountBalanceDto;
import com.acmebank.account.manager.dto.ResponseDto;
import com.acmebank.account.manager.exception.AccountManagerException;
import com.acmebank.account.manager.service.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/accounts")
@Tag(name = "Account", description = "Account API")
public class AccountController {

  private final AccountService accountService;

  public AccountController(AccountService accountService) {
    this.accountService = accountService;
  }

  @Operation(summary = "Get account balance by account number")
  @GetMapping(value = "/{accountNumber}/balances", produces = "application/json")
  public ResponseDto<AccountBalanceDto> getAccountBalances(@PathVariable String accountNumber) throws AccountManagerException {
    var account = accountService.getAccountByAccountNumber(accountNumber);
    var response = AccountBalanceDto.builder()
        .accountNumber(account.getAccountNumber())
        .balance(account.getBalance())
        .build();
    return ResponseDto.response(response);
  }


}
