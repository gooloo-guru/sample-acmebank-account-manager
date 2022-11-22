package com.acmebank.account.manager.service;


import com.acmebank.account.manager.entity.AccountTransaction;
import com.acmebank.account.manager.exception.AccountManagerException;
import com.acmebank.account.manager.service.request.TransferTransactionRequest;

public interface AccountTransactionService {
  AccountTransaction transfer(TransferTransactionRequest request) throws AccountManagerException;
}
