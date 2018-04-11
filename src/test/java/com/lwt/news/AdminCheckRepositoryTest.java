package com.lwt.news;

import com.lwt.news.repository.AdminCheckRepository;
import com.lwt.news.vo.CheckAdminVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AdminCheckRepositoryTest {

    @Autowired
    private AdminCheckRepository adminCheckRepository;

    @Test
    public void testGetAll(){
        List<Object[]> checkAdminVO = adminCheckRepository.getAll();
        checkAdminVO.forEach((obj)->{
            System.err.println(Arrays.toString(obj));
        });
        /*List<CheckAdminVO[]> checkAdminVO = adminCheckRepository.getAll();
        checkAdminVO.forEach((obj)->{
            System.err.println(Arrays.toString(obj));
        });
        System.out.println(checkAdminVO);*/
    }
}
