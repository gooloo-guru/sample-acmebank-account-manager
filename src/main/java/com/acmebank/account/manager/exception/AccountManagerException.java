package com.acmebank.account.manager.exception;

import com.acmebank.account.manager.enums.ErrorCode;
import lombok.Getter;

@Getter
public class AccountManagerException extends Exception {

  private String errorCode;

  public AccountManagerException(final String message) {
    super(message, null);
  }

  public AccountManagerException(final Throwable throwable) {
    super(null, throwable);
  }

  public AccountManagerException(final ErrorCode errorCode) {
    super(errorCode.getMessage(), null);
    this.errorCode = errorCode.getCode();
  }

}
