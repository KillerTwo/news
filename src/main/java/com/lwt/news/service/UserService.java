package com.lwt.news.service;

import com.lwt.news.dto.UserDTO;

import java.util.List;
import java.util.Map;

public interface UserService {
    /**
     * 查选所有用户信息
     * @return
     */
    List<UserDTO> findAll();

    /**
     * 根据用户身份证号查询用户信息
     * @param idCard
     * @return
     */
    UserDTO findByIdCard(String idCard);

    /**
     * 根据账号名查找用户信息
     * @param accountName
     * @return UserDTO
     */
    UserDTO findByAccountName(String accountName);

    /**
     * 添加一条用户信息
     * @param user
     */
    UserDTO saveUser(String accountName,UserDTO user);

    /**
     * 根据账号名更新一条用户信息
     * @param accountName
     * @param user
     */
    UserDTO modifyUserInfo(String accountName,UserDTO user);

    /**
     * 根据账号名删除一条用户信息
     * @param accountName
     */
    void deleteUserInfo(String accountName);

    Map<String,Object> getListByPagination(int page, int size);

    /**
     * 按条件分页查询
     * @param page
     * @param size
     * @return
     */
    public Map<String,Object> getListByPageAndCondition(int page, int size,String roleName);
}
