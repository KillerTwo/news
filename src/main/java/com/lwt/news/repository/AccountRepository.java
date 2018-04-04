package com.lwt.news.repository;

import com.lwt.news.dataobject.AccountDO;
import org.hibernate.sql.Update;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface AccountRepository extends JpaRepository<AccountDO,String> {
    AccountDO findAccountDOByAccountName(String accountName);
    AccountDO findAccountDOByAccountNameAndAccountPwd(String accountName,
                                                         String accountPwd);
    boolean existsByAccountName(String accountName);

    @Query("UPDATE AccountDO a SET accountPwd = :#{#accountDO.accountPwd} WHERE accountName =:#{#accountDO.accountName}")
    @Modifying
    int updataPwd(@Param("accountDO") AccountDO accountDO);

    @Query("UPDATE AccountDO a SET roleId = :roleId WHERE accountName = :accountName")
    @Modifying
    int updataRole(@Param("accountName") String accountName,
                   @Param("roleId") String roleId);
    List<AccountDO> findAccountDOByRoleId(String roleId);
}
