package com.lwt.news.service.impl;

import com.lwt.news.dataobject.UserDO;
import com.lwt.news.enums.CheckStateEnum;
import com.lwt.news.enums.RoleEnum;
import com.lwt.news.repository.AccountRepository;
import com.lwt.news.repository.AdminCheckRepository;
import com.lwt.news.repository.UserRepository;
import com.lwt.news.service.AdminCheckService;
import com.lwt.news.vo.CheckAdminVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class AdminCheckServiceImpl implements AdminCheckService {
    @Autowired
    private AdminCheckRepository adminCheckRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AccountRepository accountRepository;
    /**
     * 获取审核表中的所有数据
     *
     * @return
     */
    @Override
    public List<CheckAdminVO> getAll() {
        List<CheckAdminVO> voList = new ArrayList<>();
        List<Object[]> objects = adminCheckRepository.getAll();
        objects.forEach((obj)->{
            CheckAdminVO checkAdminVO = new CheckAdminVO(obj);
            voList.add(checkAdminVO);
        });
        return voList;
    }

    /**
     * 获取待审核的所有数据
     *
     * @return
     */
    @Override
    public List<CheckAdminVO> getAllByState() {
        List<CheckAdminVO> voList = new ArrayList<>();

        List<Object[]> objects = adminCheckRepository
                .getAllByState();
        objects.forEach((obj)->{
            CheckAdminVO checkAdminVO = new CheckAdminVO(obj);
            voList.add(checkAdminVO);
        });


        return voList;
    }

    /**
     * 根据是否审核通过获取审核审核表中的数据
     *
     * @param state
     * @return
     */
    @Override
    public List<CheckAdminVO> getAllByState(String state) {
        List<CheckAdminVO> voList = new ArrayList<>();
        if(state != null){
            List<Object[]> objects = adminCheckRepository
                    .getAllByState(CheckStateEnum.getCodeByInfo(state));
            objects.forEach((obj)->{
                CheckAdminVO checkAdminVO = new CheckAdminVO(obj);
                voList.add(checkAdminVO);
            });
        }

        return voList;
    }

    /**
     * 修改审核状态
     *
     * @param email    用户邮箱
     * @param state    状态
     * @param describe
     * @return 是否修改成功
     * @Param describe 未通过原因
     */
    @Override
    @Transactional
    public boolean checkUserInfo(String email, String state, String describe) {
        int effected = 0;
        //1.根据email查询用户信息
        UserDO userDO = userRepository.findByEmail(email);
        if(state.equals(CheckStateEnum.PASS.getStateInfo())){  //如果审核通过

            //2.根据用户id和审核状态更新审核表的状态字段
            effected = adminCheckRepository.modifyState(userDO.getUserId(),
                    CheckStateEnum.PASS.getCode());
            //3.通过审核后，修改账号表中的role_id字段为管理员id
            if(effected > 0)
                effected = accountRepository.updateRoleByEamil(email, RoleEnum.ADMIN.getCode());
            return effected > 0 ? true : false;
        }else{   //如果审核不通过

            //2.根据用户id和审核状态更新审核表的状态字段和未审核通过的原因
            effected = adminCheckRepository.modifyStateAndDescribe(userDO.getUserId(),
                    CheckStateEnum.NO_PASS.getCode(), describe);
            return effected > 0 ? true : false;
        }

    }
}
