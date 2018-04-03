package com.lwt.news.controller;

import com.lwt.news.dto.UserDTO;

import com.lwt.news.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 查询所有用户
     * @return
     */
    @GetMapping(value = "/users")
    public List<UserDTO> users(){
        return userService.findAll();
    }

    /**
     * 根据账号名查询一条用户信息
     * @param
     * @return
     */
    @GetMapping(value = "/user")
    public UserDTO user(){
        //从session中拿取账号名
        String accountName = "";
        return userService.findByAccountName(accountName);
    }

    /**
     * 添加一条用户数据
     * @param userDTO
     * @return
     */
    @PostMapping(value="/user")
    public UserDTO saveUser(@RequestBody UserDTO userDTO){
        //@PathVariable String accountName,
        String accountName = "";     //从Session中拿去账号名
        System.out.println(userDTO);
        System.out.println(accountName);
        return userService.saveUser(accountName,userDTO);
    }

    /**
     * 修改一条用户数据
     * @return
     */
    @PutMapping(value = "/user")
    public UserDTO updata(@RequestBody UserDTO userDTO){
        String accountName = "";      //从session中取账号名
        return userService.modifyUserInfo(accountName, userDTO);
    }

    /**
     * 删除一条用户数据
     * @param accountName
     */
    @DeleteMapping(value = "/user/{accountName}")
    public void deleteUser(@PathVariable("accountName") String accountName){

    }


}
