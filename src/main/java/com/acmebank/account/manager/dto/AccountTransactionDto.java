package com.acmebank.account.manager.dto;

import com.acmebank.account.manager.entity.AccountTransaction;
import com.acmebank.account.manager.entity.enums.Currency;
import com.acmebank.account.manager.entity.enums.TransactionStatus;
import com.acmebank.account.manager.entity.enums.TransactionType;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Data
@Builder
public class AccountTransactionDto {
  private String fromAccountNumber;
  private String toAccountNumber;
  private String transactionNumber;
  private TransactionType transactionType;
  private TransactionStatus transactionStatus;
  private Currency currency;
  private BigDecimal amount;
  private ZonedDateTime transactionTime;

  public static AccountTransactionDto fromAccountTransaction(AccountTransaction accountTransaction) {
    return AccountTransactionDto.builder()
        .fromAccountNumber(accountTransaction.getFromAccount().getAccountNumber())
        .toAccountNumber(accountTransaction.getToAccount().getAccountNumber())
        .transactionNumber(accountTransaction.getTransactionNumber().toString())
        .transactionType(accountTransaction.getTransactionType())
        .transactionStatus(accountTransaction.getTransactionStatus())
        .currency(accountTransaction.getCurrency())
        .amount(accountTransaction.getAmount())
        .transactionTime(accountTransaction.getTransactionTime())
        .build();
  }
}
