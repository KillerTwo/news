package com.lwt.news.repository;

import com.lwt.news.dataobject.UserDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<UserDO,String>{
    /**
     * 根据身份证号查询用户信息
     * @param userCard
     * @return
     */
    UserDO findUserTableByUserCard(String userCard);

    /**
     * 根据账号id查询用户信息
     * @param accountId
     * @return
     */
    UserDO findUserTableByAccountId(String accountId);

    //原生SQL实现更新方法接口
    /*@Query(value = "update user_table set ", nativeQuery = true)
    @Modifying
    void updateOne(UserDO userDO);*/

}
