package com.lwt.news.controller;

import com.lwt.news.dataobject.AccountDO;
import com.lwt.news.repository.AccountRepository;
import com.lwt.news.service.AccountService;
import com.lwt.news.util.EmailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Map;

@RestController
@SessionAttributes({"accountName","loginTime"})
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


            AccountDO reAccountDO = accountService.saveAccount(accountDO);
            if(reAccountDO != null){
                //如果注册成功，则发送激活邮件
                try {
                    EmailUtil.sendEmail(reAccountDO.getEmail(),
                            reAccountDO.getCode(), "http://localhost:8080/news/");
                }catch (Exception e){

                }
            }


        return reAccountDO;
    }


    /**
     * 账号名是否存在
     * @return
     */
    @GetMapping("/account/exit")
    public boolean exitsAccountName(String accountName){
        System.out.println("判断账号名是否存在");
        return accountService.exitAccount(accountName);
    }

    /**
     * 登陆
     * @param params
     * @return
     */
    @PostMapping(value = "/account/login")
    public boolean login(@RequestBody Map<String,Object> params,
                         Model model){
        String accountName = (String)params.get("accountName");
        String accountPwd = (String)params.get("accountPwd");
        //System.out.println(accountName+"==="+accountPwd);
        boolean isLoginSucc = accountService.findByNameAndPwd(accountName, accountPwd) == null
                ? false : true;
        //将登陆信息存入session中
        if(isLoginSucc){
            model.addAttribute("accountName",accountName);
            model.addAttribute("loginTime",new Date());
        }
        return isLoginSucc;
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
