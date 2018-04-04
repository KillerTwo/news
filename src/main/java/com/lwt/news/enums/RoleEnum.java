package com.lwt.news.enums;

public enum RoleEnum {
    ADMIN("b6438d34-295a-345f-1fcc-d75bb61b456f","普通管理员"),
    SUPER_ADMIN("588e58f8-4357-9c9e-a39c-563fabe4ce22","超级管理员"),
        REGISTER_USER("3c6e5bda-6a9d-c5f3-ac7e-c3267d75e125","注册用户");
    private String code;
    private String info;
    private RoleEnum(String code,String info){
        this.code = code;
        this.info = info;
    }

    public static String getName(String index) {
        for (RoleEnum c : RoleEnum.values()) {
            if (c.getInfo() == index) {
                return c.code;
            }
        }
        return null;
    }
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
