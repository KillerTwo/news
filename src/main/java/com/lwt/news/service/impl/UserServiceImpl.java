package com.lwt.news.service.impl;

import com.lwt.news.dataobject.AccountDO;
import com.lwt.news.dataobject.UserDO;
import com.lwt.news.dto.UserDTO;
import com.lwt.news.enums.RoleEnum;
import com.lwt.news.repository.AccountRepository;
import com.lwt.news.repository.UserRepository;
import com.lwt.news.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;


import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.*;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AccountRepository accountRepository;

    /**
     * 查选所有用户信息
     *
     * @return
     */
    @Override
    public List<UserDTO> findAll() {
        List<UserDTO> userList = new ArrayList<>();
        List<UserDO> users = userRepository.findAll();
        if(users != null)
            users.forEach((user)->{
                UserDTO userDTO = new UserDTO(user);
                userList.add(userDTO);
            });
        return userList;
    }

    /**
     * 根据用户身份证号查询用户信息
     *
     * @param idCard
     * @return
     */
    @Override
    public UserDTO findByIdCard(String idCard) {
        return null;
    }

    /**
     * 根据账号名查找用户信息
     *
     * @param accountName
     * @return UserDTO
     */
    @Override
    public UserDTO findByAccountName(String accountName) {
        String accountId = accountRepository
                .findAccountDOByAccountName(accountName).getAccountId();
        UserDO userDo = userRepository.findUserTableByAccountId(accountId);
        UserDTO userDTO = null;
        if(userDo != null)
            userDTO = new UserDTO(userDo);

        return userDTO;
    }

    /**
     * 添加一条用户信息
     *
     * @param user
     */
    @Override
    public UserDTO saveUser(String accountName,UserDTO user) {
        String accountId = accountRepository
                .findAccountDOByAccountName(accountName).getAccountId();
        UserDO userDO = new UserDO();
        userDO.init(user);
        userDO.setAccountId(accountId);
        //System.err.println(userDO);
        userDO = userRepository.save(userDO);
        return new UserDTO(userDO);
    }

    /**
     * 根据账号名更新一条用户信息
     *
     * @param accountName
     * @param user
     */
    @Override
    @Transactional
    public UserDTO modifyUserInfo(String accountName, UserDTO user) {
        String accountId = accountRepository
                .findAccountDOByAccountName(accountName).getAccountId();
        UserDO userDO = new UserDO();
        userDO.init(user);
        userDO.setAccountId(accountId);
        int effected = userRepository.updateUser(userDO);
        return effected > 0 ?
                new UserDTO(userRepository.findUserTableByAccountId(accountId)) :
                null ;
    }

    /**
     * 根据账号名删除一条用户信息
     *
     * @param accountName
     */
    @Override
    public void deleteUserInfo(String accountName) {
        String accountId = accountRepository
                .findAccountDOByAccountName(accountName).getAccountId();
        userRepository.deleteByAccountId(accountId);
    }

    /**
     * 分页查询
     * @return
     */
    @Override
    public Map<String,Object> getListByPagination(int page, int size) {
        Map<String,Object> pageMap = new HashMap<>();
        List<UserDTO> userDTOList = new ArrayList<>();
        //1.创建pageable对象
        Pageable pageable = PageRequest.of(page,size);
        //2.调用分页查询方法
        Page<UserDO> pages = userRepository.findAll(pageable);
        List<UserDO> userDOList = pages.getContent();

        userDOList.forEach((userDO)->{
            UserDTO userDTO = new UserDTO(userDO);
            userDTOList.add(userDTO);
        });
        pageMap.put("rows",userDTOList);
        pageMap.put("total",pages.getTotalElements());
        return pageMap;
    }
    /**
     * 按条件分页查询
     * @return
     */
    @Override
    public Map<String,Object> getListByPageAndCondition(int page,
                                                        int size,
                                                        String roleName) {
        Map<String,Object> pageMap = new HashMap<>();
        List<UserDTO> userDTOList = new ArrayList<>();
        String roleId = RoleEnum.getName("注册用户");
        System.out.println(roleName);
        System.out.println(roleId);
        List<AccountDO> accountDOs = accountRepository.findAccountDOByRoleId(roleId);
        List<String> accountIds = new ArrayList<>();
        accountDOs.forEach((accountDO)->{
            accountIds.add(accountDO.getAccountId());
        });
        System.out.println(accountIds);
        //1.创建Specification<T>对象
        Specification<UserDO> specification = new Specification<UserDO>() {

            @Nullable
            @Override
            public Predicate toPredicate(Root<UserDO> root,
                                         CriteriaQuery<?> query,
                                         CriteriaBuilder criteriaBuilder) {
                //List<Predicate> predicates = new ArrayList<>();
                Path<String> userAccountId = root.get("accountId");
                CriteriaBuilder.In<String> ins = criteriaBuilder.in(userAccountId);
                accountIds.forEach((accountId)->{
                    ins.value(accountId);
                   // predicates.add(ins);
                    //predicates.add(criteriaBuilder.equal(userAccountId,accountId));
                });
                //Predicate[] p = new Predicate[predicates.size()];
                //System.out.println(p);
                return ins;
            }
        };

        //1.创建pageable对象
        Pageable pageable = PageRequest.of(page,size);
        //2.调用分页查询方法
        Page<UserDO> pages = userRepository.findAll(specification,pageable);
        List<UserDO> userDOList = pages.getContent();

        userDOList.forEach((userDO)->{
            UserDTO userDTO = new UserDTO(userDO);
            userDTOList.add(userDTO);
        });
        pageMap.put("rows",userDTOList);
        pageMap.put("total",pages.getTotalElements());
        return pageMap;
    }
}
