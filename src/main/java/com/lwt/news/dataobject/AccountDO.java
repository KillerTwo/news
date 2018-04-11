package com.lwt.news.dataobject;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "account_table")
public class AccountDO implements Serializable{
    @Id
    private String accountId;
    //账号名称
    private String accountName;
    //账号密码
    private String accountPwd;
    //注册时间
    private Date registerTime;
    //激活码
    private String code;
    //是否被激活
    private int hasActivate;
    //注册邮箱
    private String email;
    //所对应的角色id
    private String roleId;

    public AccountDO() {
    }
    @GenericGenerator(name="idGenerator", strategy="uuid") //这个是hibernate的注解/生成32位UUID
    @GeneratedValue(generator="idGenerator")
    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountPwd() {
        return accountPwd;
    }

    public void setAccountPwd(String accountPwd) {
        this.accountPwd = accountPwd;
    }

    public Date getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getHasActivate() {
        return hasActivate;
    }

    public void setHasActivate(int hasActivate) {
        this.hasActivate = hasActivate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "AccountDO{" +
                "accountId='" + accountId + '\'' +
                ", accountName='" + accountName + '\'' +
                ", accountPwd='" + accountPwd + '\'' +
                ", registerTime=" + registerTime +
                ", code='" + code + '\'' +
                ", hasActivate=" + hasActivate +
                ", email='" + email + '\'' +
                ", roleId='" + roleId + '\'' +
                '}';
    }
}
