package com.lwt.news.dataobject;


import com.lwt.news.dto.UserDTO;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;


import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

/**
 * 用户表实体
 */
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name="user_table")
public class UserDO implements Serializable{

    @Id
    //主键
    @GenericGenerator(name="idGenerator", strategy="uuid") //这个是hibernate的注解/生成32位UUID
    @GeneratedValue(generator="idGenerator")
    private String userId;
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
    //用户对应的角色id
    private String accountId;

    public UserDO(){

    }
    public void init(UserDTO user){
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        this.userId = uuid;
        this.userName = user.getUserName();
        this.userAddress = user.getUserAddress();
        this.email = user.getEmail();
        this.phoneNumber = user.getPhoneNumber();
        this.userCard = user.getUserCard();
        this.userEduation = user.getUserEduation();
        this.userGraduaction = user.getUserGraduaction();
        this.userSex = user.getUserSex();
    }



    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", userAddress='" + userAddress + '\'' +
                ", userCard='" + userCard + '\'' +
                ", userSex='" + userSex + '\'' +
                ", userGraduaction='" + userGraduaction + '\'' +
                ", userEduation='" + userEduation + '\'' +
                ",  accountId='" + accountId + '\'' +
                '}';
    }
}
