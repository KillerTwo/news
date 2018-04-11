package com.lwt.news.service.impl;

import com.lwt.news.dataobject.AccountDO;
import com.lwt.news.dataobject.UserDO;
import com.lwt.news.enums.RoleEnum;
import com.lwt.news.repository.AccountRepository;
import com.lwt.news.repository.UserRepository;
import com.lwt.news.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.Date;
import java.util.UUID;
@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private UserRepository userRepository;
    /**
     * 新增一条账号信息
     *
     * @param accountDO
     * @return
     */
    @Override
    @Transactional
    public AccountDO saveAccount(AccountDO accountDO) {

        //如果用户名已经存在则返回null
        if(accountRepository.existsByAccountName(accountDO.getAccountName()) == true)
            return null;
        accountDO.setRegisterTime(new Date());
        accountDO.setRoleId(RoleEnum.REGISTER_USER.getCode());//默认为一般注册用户
        String accountId = UUID.randomUUID()
                .toString().replace("-","");
        String code = accountId +
                UUID.randomUUID()
                .toString().replace("-","");
        accountDO.setCode(code);
        accountDO.setAccountId(accountId);
        //如果创建账号成功则要在添加账号信息的同时添加一条对应的用户信息
        String userId = UUID.randomUUID()
                .toString().replace("-","");
        UserDO userDO = new UserDO();
        userDO.setAccountId(accountId);
        userDO.setEmail(accountDO.getEmail());
        AccountDO accountDOResult = accountRepository.save(accountDO);
        if(accountDOResult != null){
            userRepository.save(userDO);
        }

        return accountDOResult;
    }

    /**
     * 根据账号名修改密码字段
     *
     * @param accountDO
     * @return
     */
    @Override
    @Transactional
    public boolean modifyPassword(AccountDO accountDO) {

        return accountRepository.updataPwd(accountDO) > 0 ? true : false;
    }

    /**
     * 根据账号名修改角色设置（设置角色）
     *
     * @param accountName
     * @return
     */
    @Override
    @Transactional
    public boolean modifyRoleId(String accountName, String roleName) {
        String roleId = RoleEnum.getName(roleName);
        return accountRepository.updataRole(accountName,roleId) > 0 ? true : false;
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

    /**
     * 账号是否存在
     * @param accountName
     * @return
     */
    @Override
    public boolean exitAccount(String accountName) {
        return accountRepository.existsByAccountName(accountName);
    }

    /**
     * 激活账号
     *
     * @param email
     * @param code
     * @return
     */
    @Override
    @Transactional
    public boolean validata(String email, String code) {

        return accountRepository.updateCodeByEamil(email, code) > 0 ? true : false;
    }
}
