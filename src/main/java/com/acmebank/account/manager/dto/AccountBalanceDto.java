package com.acmebank.account.manager.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class AccountBalanceDto {
  private String accountNumber;
  private BigDecimal balance;
}
