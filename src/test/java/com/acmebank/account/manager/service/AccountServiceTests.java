package com.acmebank.account.manager.service;


import com.acmebank.account.manager.AbstractTests;
import com.acmebank.account.manager.enums.ErrorCode;
import com.acmebank.account.manager.exception.AccountManagerException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class AccountServiceTests extends AbstractTests {

  @Autowired
  private AccountService accountService;

  @Test
  public void Should_ThrowException_ForInvalidBankAccount() {
    var exception = assertThrows(AccountManagerException.class, () -> {
      var invalidBankAccount = "99999999";
      accountService.getAccountByAccountNumber(invalidBankAccount);
    });

    assertEquals(exception.getErrorCode(), ErrorCode.INVALID_BANK_ACCOUNT.getCode());
    assertEquals(exception.getMessage(), ErrorCode.INVALID_BANK_ACCOUNT.getMessage());
  }

  @Test
  public void Should_Success_ForValidBankAccount() throws AccountManagerException {
    var validBankAccount = "87654321";
    var account = accountService.getAccountByAccountNumber(validBankAccount);

    assertNotNull(account);
    assertEquals(account.getAccountNumber(), validBankAccount);
    assertEquals(account.getBalance(), new BigDecimal("1000.00"));
  }

}
