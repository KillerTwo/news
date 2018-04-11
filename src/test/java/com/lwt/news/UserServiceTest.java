package com.lwt.news;


import com.lwt.news.dto.UserDTO;
import com.lwt.news.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {
    @Autowired
    private UserService userService;
    @Test
    public void testGetListByPagination(){
       Map<String, Object> modMap = userService.getListByPagination(1,5);
       //assertEquals(5,(List)modMap.get("rows").size());
        List<UserDTO> list = (List<UserDTO>) modMap.get("rows");
        list.forEach((userDTO)->{
            System.err.println(userDTO.toString());
        });
        //System.err.println(modMap.get("rows"));
    }
}
