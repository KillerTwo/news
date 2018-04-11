package com.lwt.news.repository;

import com.lwt.news.dataobject.CheckAdminDO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * 审核相关的数据访问方法
 */
public interface AdminCheckRepository extends JpaRepository<CheckAdminDO,String> {
    @Query(value = "SELECT user_name,user_address,user_card,user_eduation,user_graduaction,user_sex,phone_number,email,c.pass_check AS state FROM user_table u,check_admin c WHERE c.pass_check = 0 AND u.user_id=c.user_id",
            nativeQuery=true)
    /**
     * Object[]中放置的是一个表的字段，objcte[0]位第一个字段，objcte[1]位第二个字段...
     * 查询所有待审核的用户信息
     */
    List<Object[]> getAllByState();
   // List<CheckAdminVO[]> getAll();
   @Query(value = "SELECT user_name,user_address,user_card,user_eduation,user_graduaction,user_sex,phone_number,email,c.pass_check AS state FROM user_table u,check_admin c WHERE c.pass_check = :state AND u.user_id=c.user_id",
           nativeQuery=true)
   /**
    * Object[]中放置的是一个表的字段，objcte[0]位第一个字段，objcte[1]位第二个字段...
    *根据是否通过审核查询所有用户信息
    */
   List<Object[]> getAllByState(@Param("state") int state);

    @Query(value = "SELECT user_name,user_address,user_card,user_eduation,user_graduaction,user_sex,phone_number,email,c.pass_check AS state FROM user_table u,check_admin c WHERE u.user_id=c.user_id",
            nativeQuery=true)
    /**
     * Object[]中放置的是一个表的字段，objcte[0]位第一个字段，objcte[1]位第二个字段...
     *查询所有审核表里的用户信息
     */
    List<Object[]> getAll();

    @Query(value = "UPDATE check_admin SET pass_check=:state WHERE user_id=:userId",
            nativeQuery=true)
    @Modifying
    /**
     *
     *更新审核状态（审核通过）|用户提交审核时调用该方法将状态置为待审核（0）
     */
    int modifyState(@Param("userId") String userId, @Param("state") int state);

    @Query(value = "UPDATE check_admin SET pass_check=:state,cause_desc=:causeDesc WHERE user_id=:userId",
            nativeQuery=true)
    @Modifying
    /**
     *
     *更新审核状态(未审核通过)
     */
    int modifyStateAndDescribe(@Param("userId") String userId,
                               @Param("state") int state,
                                @Param("causeDesc") String causeDesc);
}
