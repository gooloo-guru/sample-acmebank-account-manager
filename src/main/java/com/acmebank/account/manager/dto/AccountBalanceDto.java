package com.acmebank.account.manager.dto;

import com.acmebank.account.manager.entity.Account;
import com.acmebank.account.manager.entity.enums.Currency;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class AccountBalanceDto {
  @Schema(example = "88888888")
  private String accountNumber;

  @Schema(example = "HKD")
  private Currency currency;

  @Schema(example = "100.00")
  private BigDecimal balance;

  public static AccountBalanceDto fromAccount(Account account) {
    return AccountBalanceDto.builder()
        .accountNumber(account.getAccountNumber())
        .balance(account.getBalance())
        .currency(account.getCurrency())
        .build();
  }
}
