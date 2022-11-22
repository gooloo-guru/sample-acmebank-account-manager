package com.acmebank.account.manager.dto;

import com.acmebank.account.manager.entity.AccountTransaction;
import com.acmebank.account.manager.entity.constant.Currency;
import com.acmebank.account.manager.entity.constant.TransactionStatus;
import com.acmebank.account.manager.entity.constant.TransactionType;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Data
@Builder
public class AccountTransactionDto {
  private String fromAccountNumber;
  private String toAccountNumber;
  private TransactionType transactionType;
  private TransactionStatus transactionStatus;
  private Currency currency;
  private BigDecimal amount;
  private ZonedDateTime transactionTime;

  public static AccountTransactionDto fromAccountTransaction(AccountTransaction accountTransaction) {
    return AccountTransactionDto.builder()
        .fromAccountNumber(accountTransaction.getFromAccount().getAccountNumber())
        .toAccountNumber(accountTransaction.getToAccount().getAccountNumber())
        .transactionType(accountTransaction.getTransactionType())
        .transactionStatus(accountTransaction.getTransactionStatus())
        .currency(accountTransaction.getCurrency())
        .amount(accountTransaction.getAmount())
        .transactionTime(accountTransaction.getTransactionTime())
        .build();
  }
}
