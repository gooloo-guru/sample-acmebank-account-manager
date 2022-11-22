package com.acmebank.account.manager.dto;

import com.acmebank.account.manager.entity.constant.Currency;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransferTransactionRequestDto {
  @NotBlank
  private Currency currency;

  @Positive
  private BigDecimal amount;

  @NotBlank
  private String fromAccountNumber;

  @NotBlank
  private String toAccountNumber;
}
