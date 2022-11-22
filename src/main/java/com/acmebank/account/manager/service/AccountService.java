package com.acmebank.account.manager.service;

import com.acmebank.account.manager.entity.Account;
import com.acmebank.account.manager.exception.AccountManagerException;

public interface AccountService {

  Account getAccountByAccountNumber(String accountNumber) throws AccountManagerException;
}
