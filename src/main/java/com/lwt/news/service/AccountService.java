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
    boolean modifyPassword(AccountDO accountDO);

    /**
     * 根据账号名修改角色设置（设置角色）
     * @param roleName
     * @return
     */
    boolean modifyRoleId(String accountName, String roleName);

    /**
     * 根据账号名和密码查找账号
     * @param accountName
     * @param accountPwd
     * @return
     */
    AccountDO findByNameAndPwd(String accountName, String accountPwd);

    /**
     * 判断账号是否存在
     * @param accountName
     * @return
     */
    boolean exitAccount(String accountName);

    /**
     * 激活账号
     * @param email
     * @param code
     * @return
     */
    boolean validata(String email, String code);
}
