package com.lwt.news;

import com.lwt.news.service.AdminCheckService;
import com.lwt.news.vo.CheckAdminVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AdminCheckServiceTest {
    @Autowired
    private AdminCheckService adminCheckService;
    @Test
    public void testGetAll(){
        List<CheckAdminVO> checkAdminVOS = adminCheckService.getAll();
        checkAdminVOS.forEach((checkAdminVO)->{
            System.err.println(checkAdminVO);
        });
        System.out.println();
    }
    @Test
    public void testgetAllByState(){
        List<CheckAdminVO> checkAdminVOS = adminCheckService.getAllByState("通过审核");
        checkAdminVOS.forEach((checkAdminVO)->{
            System.err.println(checkAdminVO);
        });
        System.out.println();
    }
   /* @Test
    public void testgetAllByStatestate(){
        List<CheckAdminVO> checkAdminVOS = adminCheckService.getAll();
        checkAdminVOS.forEach((checkAdminVO)->{
            System.err.println(checkAdminVO);
        });
        System.out.println();
    }*/
    @Test
    public void testcheckUserInfo(){
        boolean isSucc = adminCheckService.checkUserInfo("lwt2710@outlook.com",
                "未通过","用户名不合法");

        System.out.println(isSucc);
    }

}
