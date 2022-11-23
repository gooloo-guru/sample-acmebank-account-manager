package com.acmebank.account.manager.dto;

import com.acmebank.account.manager.entity.AccountTransaction;
import com.acmebank.account.manager.entity.enums.Currency;
import com.acmebank.account.manager.entity.enums.TransactionStatus;
import com.acmebank.account.manager.entity.enums.TransactionType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Data
@Builder
public class AccountTransactionDto {
  @Schema(example = "12345678")
  private String fromAccountNumber;

  @Schema(example = "88888888")
  private String toAccountNumber;

  @Schema(example = "123e4567-e89b-12d3-a456-426614174000")
  private String transactionNumber;

  @Schema(example = "TRANSFER")
  private TransactionType transactionType;

  @Schema(example = "COMPLETED")
  private TransactionStatus transactionStatus;

  @Schema(example = "HKD")
  private Currency currency;

  @Schema(example = "1000.00")
  private BigDecimal amount;

  @Schema(example = "2022-11-23T01:30:15.099Z")
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
