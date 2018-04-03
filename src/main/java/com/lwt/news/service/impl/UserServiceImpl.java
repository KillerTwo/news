package com.lwt.news.service.impl;

import com.lwt.news.dataobject.UserDO;
import com.lwt.news.dto.UserDTO;
import com.lwt.news.repository.AccountRepository;
import com.lwt.news.repository.UserRepository;
import com.lwt.news.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
        System.err.println(userDO);
        userDO = userRepository.save(userDO);
        return new UserDTO(userDO);
    }
/***********************************************************/
    /**
     * 根据账号名更新一条用户信息
     *
     * @param accountName
     * @param user
     */
    @Override
    public UserDTO modifyUserInfo(String accountName, UserDTO user) {
        String accountId = accountRepository
                .findAccountDOByAccountName(accountName).getAccountId();
        UserDO userDO = new UserDO();
        userDO.init(user);
        userDO.setAccountId(accountId);
        return null;
    }

    /**
     * 根据账号名删除一条用户信息
     *
     * @param accountName
     */
    @Override
    public void deleteUserInfo(String accountName) {

    }
}
