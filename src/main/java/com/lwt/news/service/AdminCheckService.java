package com.lwt.news.service;

import com.lwt.news.vo.CheckAdminVO;

import java.util.List;

public interface AdminCheckService {
    /**
     * 获取审核表中的所有数据
     * @return
     */
    List<CheckAdminVO> getAll();

    /**
     * 获取待审核的所有数据
     * @return
     */
    List<CheckAdminVO> getAllByState();

    /**
     * 根据是否审核通过获取审核审核表中的数据
     * @param state
     * @return
     */
    List<CheckAdminVO> getAllByState(String state);

    /**
     * 修改审核状态
     * @param email     用户邮箱
     * @param state     状态
     * @Param describe 未通过原因
     * @return  是否修改成功
     */
    boolean checkUserInfo(String email, String state, String describe);
}
