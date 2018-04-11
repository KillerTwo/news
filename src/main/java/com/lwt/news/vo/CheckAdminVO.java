package com.lwt.news.vo;

import com.lwt.news.enums.CheckStateEnum;

import java.util.zip.Checksum;

/**
 * 审核管理员申请VO对象
 */
public class CheckAdminVO {
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
    //审核状态（审核通过，待审核，审核未通过）
    private String state;

    public CheckAdminVO() {
    }
    //phone_number,email,c.pass_check
    public CheckAdminVO(String userName,
                        String userAddress,
                        String userCard,
                        String userEduation,
                        String userGraduaction,
                        String userSex,
                        String phoneNumber,
                        String email,
                        String state) {
        this.userName = userName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.userAddress = userAddress;
        this.userCard = userCard;
        this.userSex = userSex;
        this.userGraduaction = userGraduaction;
        this.userEduation = userEduation;
        this.state = state;
    }
    public CheckAdminVO(Object[] obj) {
        this.userName = (String) obj[0];
        this.phoneNumber = (String) obj[1];
        this.email = (String) obj[2];
        this.userAddress = (String) obj[3];
        this.userCard = (String) obj[4];
        this.userSex = (String) obj[5];
        this.userGraduaction = (String) obj[6];
        this.userEduation = (String) obj[7];
        this.state = CheckStateEnum.getInfoByCode((int)obj[8]);
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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "CheckAdminVO{" +
                "userName='" + userName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", userAddress='" + userAddress + '\'' +
                ", userCard='" + userCard + '\'' +
                ", userSex='" + userSex + '\'' +
                ", userGraduaction='" + userGraduaction + '\'' +
                ", userEduation='" + userEduation + '\'' +
                ", accountName='" + accountName + '\'' +
                ", state='" + state + '\'' +
                '}';
    }
}
