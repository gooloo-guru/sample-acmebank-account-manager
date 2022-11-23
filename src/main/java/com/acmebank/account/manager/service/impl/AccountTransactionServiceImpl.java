package com.acmebank.account.manager.service.impl;

import com.acmebank.account.manager.enums.ErrorCode;
import com.acmebank.account.manager.entity.AccountTransaction;
import com.acmebank.account.manager.entity.enums.TransactionStatus;
import com.acmebank.account.manager.entity.enums.TransactionType;
import com.acmebank.account.manager.exception.AccountManagerException;
import com.acmebank.account.manager.respository.AccountRepository;
import com.acmebank.account.manager.respository.AccountTransactionRepository;
import com.acmebank.account.manager.service.AccountTransactionService;
import com.acmebank.account.manager.service.request.TransferTransactionRequest;
import com.acmebank.account.manager.util.TimeUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.UUID;

@Service
public class AccountTransactionServiceImpl implements AccountTransactionService {

  private final AccountRepository accountRepository;
  private final AccountTransactionRepository accountTransactionRepository;

  @Autowired
  public AccountTransactionServiceImpl(AccountRepository accountRepository, AccountTransactionRepository accountTransactionRepository) {
    this.accountRepository = accountRepository;
    this.accountTransactionRepository = accountTransactionRepository;
  }

  @Transactional
  @Override
  public AccountTransaction transfer(TransferTransactionRequest request) throws AccountManagerException {
    if (StringUtils.isBlank(request.getFromAccountNumber()) || StringUtils.isBlank(request.getToAccountNumber())) {
      throw new AccountManagerException(ErrorCode.INVALID_BANK_ACCOUNT);
    }

    if (request.getAmount().compareTo(BigDecimal.ZERO) < 0) {
      throw new AccountManagerException(ErrorCode.INVALID_TRANSACTION_AMOUNT);
    }

    var fromAccount = accountRepository.findOneByAccountNumber(request.getFromAccountNumber())
        .orElseThrow(() -> new AccountManagerException(ErrorCode.DEBIT_ACCOUNT_NOT_FOUND));

    var toAccount = accountRepository.findOneByAccountNumber(request.getToAccountNumber())
        .orElseThrow(() -> new AccountManagerException(ErrorCode.CREDIT_ACCOUNT_NOT_FOUND));

    if (fromAccount.getBalance().compareTo(request.getAmount()) < 0) {
      throw new AccountManagerException(ErrorCode.INSUFFICIENT_ACCOUNT_BALANCE);
    }

    var accountTransaction = new AccountTransaction();
    accountTransaction.setFromAccount(fromAccount);
    accountTransaction.setToAccount(toAccount);
    accountTransaction.setTransactionNumber(UUID.randomUUID());
    accountTransaction.setTransactionType(TransactionType.TRANSFER);
    accountTransaction.setTransactionStatus(TransactionStatus.COMPLETED);
    accountTransaction.setCurrency(request.getCurrency());
    accountTransaction.setAmount(request.getAmount());
    accountTransaction.setTransactionTime(TimeUtil.toUtcZoneDateTime());
    accountTransactionRepository.save(accountTransaction);

    fromAccount.setBalance(fromAccount.getBalance().subtract(request.getAmount()));
    toAccount.setBalance(toAccount.getBalance().add(request.getAmount()));

    return accountTransaction;
  }
}
