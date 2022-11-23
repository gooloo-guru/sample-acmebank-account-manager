package com.acmebank.account.manager.service.impl;

import com.acmebank.account.manager.enums.ErrorCode;
import com.acmebank.account.manager.entity.Account;
import com.acmebank.account.manager.exception.AccountManagerException;
import com.acmebank.account.manager.respository.AccountRepository;
import com.acmebank.account.manager.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class AccountServiceImpl implements AccountService {

  private final AccountRepository accountRepository;

  @Autowired
  public AccountServiceImpl(AccountRepository accountRepository) {
    this.accountRepository = accountRepository;
  }

  @Transactional(readOnly = true)
  @Override
  public Account getAccountByAccountNumber(String accountNumber) throws AccountManagerException {
    var account = accountRepository.findOneByAccountNumber(accountNumber)
        .orElseThrow(() -> new AccountManagerException(ErrorCode.INVALID_BANK_ACCOUNT));
    return account;
  }

}
