package com.lwt.news.dto;

import com.lwt.news.dataobject.UserDO;

public class UserDTO {

    //用户姓名
    private String userName;
    //用户电话
    private String phoneNumber;
    //用户邮箱
    private String email;
    //用户地址
    private String userAddress;
    //用户身份证号
    private String userCard;
    //用户性别
    private String userSex;
    //毕业学校
    private String userGraduaction;
    //学历
    private String userEduation;
    //该用户对应的账号名
    private String accountName;
    public UserDTO(){}

    public UserDTO(UserDO user){
        this.userName = user.getUserName();
        this.userAddress = user.getUserAddress();
        this.email = user.getEmail();
        this.phoneNumber = user.getPhoneNumber();
        this.userCard = user.getUserCard();
        this.userEduation = user.getUserEduation();
        this.userGraduaction = user.getUserGraduaction();
        this.userSex = user.getUserSex();

    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public String getUserCard() {
        return userCard;
    }

    public void setUserCard(String userCard) {
        this.userCard = userCard;
    }

    public String getUserSex() {
        return userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex;
    }

    public String getUserGraduaction() {
        return userGraduaction;
    }

    public void setUserGraduaction(String userGraduaction) {
        this.userGraduaction = userGraduaction;
    }

    public String getUserEduation() {
        return userEduation;
    }

    public void setUserEduation(String userEduation) {
        this.userEduation = userEduation;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "userName='" + userName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", userAddress='" + userAddress + '\'' +
                ", userCard='" + userCard + '\'' +
                ", userSex='" + userSex + '\'' +
                ", userGraduaction='" + userGraduaction + '\'' +
                ", userEduation='" + userEduation + '\'' +
                '}';
    }
}
