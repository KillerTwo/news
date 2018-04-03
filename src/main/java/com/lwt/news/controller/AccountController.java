package com.lwt.news.controller;

import com.lwt.news.dataobject.AccountDO;
import com.lwt.news.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class AccountController {
    @Autowired
    private AccountService accountService;

    /**
     * 注册
     * @param accountDO
     * @return
     */
    @PostMapping(value = "/account/register")
    public AccountDO save(@RequestBody AccountDO accountDO){
        return accountService.saveAccount(accountDO);
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
}
