package com.lwt.news.controller;

import com.lwt.news.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class ViewController {
    @Autowired
    private AccountService accountService;
   @RequestMapping("/home")
    public String homePage(){
        //System.out.println("请求首页");
        return "/home.html";
    }
    /**
     * 激活账号
     * @param email
     * @param code
     * @return
     */
    @GetMapping(value = "/account/code")
    public String validatCode(@RequestParam String email,
                              @RequestParam String code){

        return accountService.validata(email, code) ?
                "/valid.html":"<h3>验证失败，请检查验证时间是否超时</h3>";
    }
}
