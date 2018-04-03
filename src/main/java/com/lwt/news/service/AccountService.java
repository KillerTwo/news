package com.lwt.news.service;

import com.lwt.news.dataobject.AccountDO;

public interface AccountService {
    /**
     * 新增一条账号信息
     * @param accountDO
     * @return
     */
    AccountDO saveAccount(AccountDO accountDO);

    /**
     * 根据账号名修改密码字段
     * @param accountDO
     * @return
     */
    AccountDO modifyPassword(AccountDO accountDO);

    /**
     * 根据账号名修改角色设置（设置角色）
     * @param accountDOa
     * @return
     */
    AccountDO modifyRoleId(AccountDO accountDOa);
    AccountDO findByNameAndPwd(String accountName, String accountPwd);

}
