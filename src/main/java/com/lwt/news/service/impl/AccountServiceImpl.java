package com.lwt.news.service.impl;

import com.lwt.news.dataobject.AccountDO;
import com.lwt.news.enums.RoleEnum;
import com.lwt.news.repository.AccountRepository;
import com.lwt.news.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.UUID;
@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountRepository accountRepository;
    /**
     * 新增一条账号信息
     *
     * @param accountDO
     * @return
     */
    @Override
    @Transactional
    public AccountDO saveAccount(AccountDO accountDO) {
        AccountDO oldAccountDao = accountRepository
                .findAccountDOByAccountName(accountDO.getAccountName());
        if(oldAccountDao != null)
            return null;
        accountDO.setRegisterTime(new Date());
        accountDO.setRoleId(RoleEnum.REGISTER_USER.getCode());//默认为一般注册用户
        accountDO.setAccountId(UUID.randomUUID()
                .toString().replace("-",""));
        return accountRepository.save(accountDO);
    }

    /**
     * 根据账号名修改密码字段
     *
     * @param accountDO
     * @return
     */
    @Override
    public AccountDO modifyPassword(AccountDO accountDO) {
        return null;
    }

    /**
     * 根据账号名修改角色设置（设置角色）
     *
     * @param accountDOa
     * @return
     */
    @Override
    public AccountDO modifyRoleId(AccountDO accountDOa) {
        return null;
    }

    /**
     * 根据账号名和密码查询账号信息
     * @param accountName
     * @param accountPwd
     * @return
     */
    @Override
    public AccountDO findByNameAndPwd(String accountName, String accountPwd) {
        return accountRepository.findAccountDOByAccountNameAndAccountPwd(accountName,
                accountPwd);
    }
}
