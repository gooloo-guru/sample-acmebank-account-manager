package com.acmebank.account.manager.entity;

import com.acmebank.account.manager.entity.enums.Currency;
import com.acmebank.account.manager.entity.enums.TransactionStatus;
import com.acmebank.account.manager.entity.enums.TransactionType;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.UUID;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "account_transaction")
public class AccountTransaction implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @EqualsAndHashCode.Include
  @Column(name = "account_transaction_id", nullable = false)
  private Long accountTransactionId;

  @ManyToOne
  @JoinColumn(name = "from_account_id")
  private Account fromAccount;

  @ManyToOne
  @JoinColumn(name = "to_account_id")
  private Account toAccount;

  @Column(name = "transaction_number", nullable = false)
  private UUID transactionNumber;

  @Enumerated(EnumType.ORDINAL)
  @Column(name = "transaction_type", nullable = false)
  private TransactionType transactionType;

  @Enumerated(EnumType.ORDINAL)
  @Column(name = "transaction_status", nullable = false)
  private TransactionStatus transactionStatus;

  @Enumerated(EnumType.STRING)
  @Column(name = "currency", nullable = false)
  private Currency currency;

  @Column(name = "amount", nullable = false)
  private BigDecimal amount = BigDecimal.ZERO;

  @Column(name = "transaction_time", nullable = false)
  private ZonedDateTime transactionTime;
}
