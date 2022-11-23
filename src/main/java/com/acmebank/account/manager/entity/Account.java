package com.acmebank.account.manager.entity;

import com.acmebank.account.manager.entity.enums.AccountType;
import com.acmebank.account.manager.entity.enums.Currency;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "account")
public class Account implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "account_id", nullable = false)
  @EqualsAndHashCode.Include
  private Long accountId;

  @Column(name = "account_number", nullable = false, length = 8)
  private String accountNumber;

  @Enumerated(EnumType.ORDINAL)
  @Column(name = "account_type", nullable = false)
  private AccountType accountType;

  @Enumerated(EnumType.STRING)
  @Column(name = "currency", nullable = false)
  private Currency currency;

  @Column(name = "balance", nullable = false)
  private BigDecimal balance;

}
