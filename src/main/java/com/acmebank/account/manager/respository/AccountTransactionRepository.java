package com.acmebank.account.manager.respository;

import com.acmebank.account.manager.entity.AccountTransaction;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountTransactionRepository extends PagingAndSortingRepository<AccountTransaction, Long>, JpaSpecificationExecutor<AccountTransaction> {

}
