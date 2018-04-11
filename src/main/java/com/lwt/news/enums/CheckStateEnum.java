package com.lwt.news.enums;

public enum CheckStateEnum {
    PASS(1,"通过审核"),NO_PASS(-1,"未通过"),WAIT(0,"待审核");
    private Integer code;
    private String stateInfo;

    CheckStateEnum(Integer code, String stateInfo) {
        this.code = code;
        this.stateInfo = stateInfo;
    }

    /**
     * 根据字符串返回code
     * @param index
     * @return
     */
    public static Integer getCodeByInfo(String index) {
        for (CheckStateEnum c : CheckStateEnum.values()) {
            if (c.getStateInfo() == index) {
                return c.code;
            }
        }
        return null;
    }
    public static String getInfoByCode(int code) {
        for (CheckStateEnum c : CheckStateEnum.values()) {
            if (c.getCode() == code) {
                return c.stateInfo;
            }
        }
        return null;
    }
    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public void setStateInfo(String stateInfo) {
        this.stateInfo = stateInfo;
    }
}
