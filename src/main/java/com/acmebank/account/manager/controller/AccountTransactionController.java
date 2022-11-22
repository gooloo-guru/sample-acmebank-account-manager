package com.acmebank.account.manager.controller;

import com.acmebank.account.manager.dto.AccountTransactionDto;
import com.acmebank.account.manager.dto.ResponseDto;
import com.acmebank.account.manager.dto.TransferTransactionRequestDto;
import com.acmebank.account.manager.exception.AccountManagerException;
import com.acmebank.account.manager.service.AccountTransactionService;
import com.acmebank.account.manager.service.request.TransferTransactionRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("v1/account-transactions")
@Tag(name = "Account Transaction", description = "Account Transaction API")
public class AccountTransactionController {

  private final AccountTransactionService accountTransactionService;

  public AccountTransactionController(AccountTransactionService accountTransactionService) {
    this.accountTransactionService = accountTransactionService;
  }

  @Operation(summary = "Transfer money between accounts")
  @PostMapping(value = "/transfer-transactions", produces = "application/json")
  public ResponseDto<AccountTransactionDto> transfer(TransferTransactionRequestDto request) throws AccountManagerException {
    var transactionRequest = TransferTransactionRequest.fromTransferTransactionRequestDto(request);
    var accountTransaction = accountTransactionService.transfer(transactionRequest);
    return ResponseDto.response(AccountTransactionDto.fromAccountTransaction(accountTransaction));
  }


}
