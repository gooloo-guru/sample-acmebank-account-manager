package com.acmebank.account.manager.dto;

import com.acmebank.account.manager.entity.enums.Currency;
import io.swagger.v3.oas.annotations.media.Schema;
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
  @Schema(example = "HKD")
  @NotBlank
  private Currency currency;

  @Schema(example = "1000.00")
  @Positive
  private BigDecimal amount;

  @Schema(example = "12345678")
  @NotBlank
  private String fromAccountNumber;

  @Schema(example = "88888888")
  @NotBlank
  private String toAccountNumber;
}
