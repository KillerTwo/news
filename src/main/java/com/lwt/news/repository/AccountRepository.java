package com.lwt.news.repository;

import com.lwt.news.dataobject.AccountDO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<AccountDO,String> {
    AccountDO findAccountDOByAccountName(String accountName);
    AccountDO findAccountDOByAccountNameAndAccountPwd(String accountName,
                                                         String accountPwd);
}
