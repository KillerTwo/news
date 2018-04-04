package com.lwt.news.controller;

import com.lwt.news.dataobject.AccountDO;
import com.lwt.news.repository.AccountRepository;
import com.lwt.news.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class AccountController {
    @Autowired
    private AccountService accountService;
    @Autowired
    private AccountRepository accountRepository;
    /**
     * 注册
     * @param accountDO
     * @return
     */
    @PostMapping(value = "/account/register")
    public AccountDO save(@RequestBody AccountDO accountDO){
        //System.out.println(accountRepository.existsByAccountName(accountDO.getAccountName()));
        return accountService.saveAccount(accountDO);
    }

    /**
     * 账号名是否存在
     * @return
     */
    @GetMapping("/account/exit")
    public boolean exitsAccountName(String accountName){
        return accountService.exitAccount(accountName);
    }

    /**
     * 登陆
     * @param params
     * @return
     */
    @PostMapping(value = "/account/login")
    public boolean login(@RequestBody Map<String,Object> params){
        String accountName = (String)params.get("accountName");
        String accountPwd = (String)params.get("accountPwd");
        System.out.println(accountName+"==="+accountPwd);
        return accountService.findByNameAndPwd(accountName, accountPwd) == null
                ? false : true;
    }

    /**
     * 修改密码
     * @param accountDO
     * @return
     */
    @PutMapping(value = "/account/putpwd")
    public boolean modifyPwd(@RequestBody AccountDO accountDO){
        return accountService.modifyPassword(accountDO);
    }

    /**
     * 修改账号角色
     * @return
     */
    @PutMapping(value = "/account/putrole")
    public boolean modifyRole(@RequestParam String accountName,
                              @RequestParam String roleName){
        //System.out.println(""+accountName + " "+ roleName);
        return accountService.modifyRoleId(accountName, roleName);
    }

}
