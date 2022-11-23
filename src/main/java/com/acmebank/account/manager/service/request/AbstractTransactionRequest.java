package com.acmebank.account.manager.service.request;

import com.acmebank.account.manager.entity.enums.Currency;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public abstract class AbstractTransactionRequest {
  protected Currency currency;
  protected BigDecimal amount;
}
