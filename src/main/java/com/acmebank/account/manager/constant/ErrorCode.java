package com.acmebank.account.manager.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

  INVALID_BANK_ACCOUNT("E1001", "Invalid bank account"),
  INSUFFICIENT_ACCOUNT_BALANCE("E1002", "Insufficient account balance"),
  CREDIT_ACCOUNT_NOT_FOUND("E1003", "Credit account not found"),
  DEBIT_ACCOUNT_NOT_FOUND("E1004", "Debit account not found"),
  INVALID_TRANSACTION_AMOUNT("E1005", "Invalid transaction amount");
  public static final String DEFAULT_ERROR_CODE = "UNSPECIFIED";
  public static final String DEFAULT_ERROR_MESSAGE = "Internal server error";

  private String code;

  private String message;
}
