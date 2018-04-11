package com.lwt.news.dataobject;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "check_admin")
public class CheckAdminDO {
    @Id
    private String check_id;
    //是否通过审核
    private int pass_check;
    //用户id
    private String user_id;
    //未审核通过的原因
    private String causeDesc;

    @GenericGenerator(name="idGenerator", strategy="uuid") //这个是hibernate的注解/生成32位UUID
    @GeneratedValue(generator="idGenerator")
    public String getCheck_id() {
        return check_id;
    }

    public void setCheck_id(String check_id) {
        this.check_id = check_id;
    }

    public int getPass_check() {
        return pass_check;
    }

    public void setPass_check(int pass_check) {
        this.pass_check = pass_check;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
}
