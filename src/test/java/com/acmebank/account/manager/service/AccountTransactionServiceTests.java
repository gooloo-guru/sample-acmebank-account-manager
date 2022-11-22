package com.acmebank.account.manager.service;

import com.acmebank.account.manager.AbstractTests;
import com.acmebank.account.manager.constant.ErrorCode;
import com.acmebank.account.manager.entity.constant.Currency;
import com.acmebank.account.manager.entity.constant.TransactionStatus;
import com.acmebank.account.manager.entity.constant.TransactionType;
import com.acmebank.account.manager.exception.AccountManagerException;
import com.acmebank.account.manager.service.request.TransferTransactionRequest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
public class AccountTransactionServiceTests extends AbstractTests {

  @Autowired
  private AccountTransactionService accountTransactionService;


  @Test
  public void Should_ThrowException_ForInvalidBankAccount() {
    var exception = assertThrows(AccountManagerException.class, () -> {

      var request = TransferTransactionRequest.builder()
          .toAccountNumber("")
          .fromAccountNumber("")
          .build();
      request.setAmount(BigDecimal.ONE);
      request.setCurrency(Currency.HKD);

      accountTransactionService.transfer(request);
    });

    assertEquals(exception.getErrorCode(), ErrorCode.INVALID_BANK_ACCOUNT.getCode());
    assertEquals(exception.getMessage(), ErrorCode.INVALID_BANK_ACCOUNT.getMessage());
  }

  @Test
  public void Should_ThrowException_ForInvalidTransaction() {
    var exception = assertThrows(AccountManagerException.class, () -> {

      var request = TransferTransactionRequest.builder()
          .toAccountNumber("88888888")
          .fromAccountNumber("12345678")
          .build();
      request.setAmount(BigDecimal.valueOf(-0.1));
      request.setCurrency(Currency.HKD);

      accountTransactionService.transfer(request);
    });

    assertEquals(exception.getErrorCode(), ErrorCode.INVALID_TRANSACTION_AMOUNT.getCode());
    assertEquals(exception.getMessage(), ErrorCode.INVALID_TRANSACTION_AMOUNT.getMessage());
  }

  @Test
  public void Should_ThrowException_ForDebitAccountNotFound() {
    var exception = assertThrows(AccountManagerException.class, () -> {

      var request = TransferTransactionRequest.builder()
          .toAccountNumber("88888888")
          .fromAccountNumber("XXXXXXXX")
          .build();
      request.setAmount(BigDecimal.TEN);
      request.setCurrency(Currency.HKD);

      accountTransactionService.transfer(request);
    });

    assertEquals(exception.getErrorCode(), ErrorCode.DEBIT_ACCOUNT_NOT_FOUND.getCode());
    assertEquals(exception.getMessage(), ErrorCode.DEBIT_ACCOUNT_NOT_FOUND.getMessage());
  }

  @Test
  public void Should_ThrowException_ForCreditAccountNotFound() {
    var exception = assertThrows(AccountManagerException.class, () -> {

      var request = TransferTransactionRequest.builder()
          .toAccountNumber("XXXXXXXX")
          .fromAccountNumber("12345678")
          .build();
      request.setAmount(BigDecimal.TEN);
      request.setCurrency(Currency.HKD);

      accountTransactionService.transfer(request);
    });

    assertEquals(exception.getErrorCode(), ErrorCode.CREDIT_ACCOUNT_NOT_FOUND.getCode());
    assertEquals(exception.getMessage(), ErrorCode.CREDIT_ACCOUNT_NOT_FOUND.getMessage());
  }

  @Test
  public void Should_ThrowException_ForInsufficientAccountBalance() {
    var exception = assertThrows(AccountManagerException.class, () -> {

      var request = TransferTransactionRequest.builder()
          .toAccountNumber("12345678")
          .fromAccountNumber("88888888")
          .build();
      request.setAmount(BigDecimal.valueOf(10_000_000));
      request.setCurrency(Currency.HKD);

      accountTransactionService.transfer(request);
    });

    assertEquals(exception.getErrorCode(), ErrorCode.INSUFFICIENT_ACCOUNT_BALANCE.getCode());
    assertEquals(exception.getMessage(), ErrorCode.INSUFFICIENT_ACCOUNT_BALANCE.getMessage());
  }

  @Test
  public void Should_TransferSuccess_ForValidAccountTransaction() throws AccountManagerException {
    var request = TransferTransactionRequest.builder()
        .toAccountNumber("88888888")
        .fromAccountNumber("12345678")
        .build();
    request.setAmount(BigDecimal.TEN);
    request.setCurrency(Currency.HKD);

    var accountTransaction = accountTransactionService.transfer(request);

    assertNotNull(accountTransaction);
    assertEquals(accountTransaction.getFromAccount().getAccountNumber(), "12345678");
    assertEquals(accountTransaction.getToAccount().getAccountNumber(), "88888888");
    assertEquals(accountTransaction.getAmount(), BigDecimal.TEN);
    assertEquals(accountTransaction.getCurrency(), Currency.HKD);
    assertEquals(accountTransaction.getTransactionType(), TransactionType.TRANSFER);
    assertEquals(accountTransaction.getTransactionStatus(), TransactionStatus.COMPLETED);
  }
}