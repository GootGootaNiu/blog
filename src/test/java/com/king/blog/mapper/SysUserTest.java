package com.king.blog.mapper;


import com.king.blog.pojo.SysUser;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
public class SysUserTest {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Test
    public void selectAll(){
        List<SysUser> sysUsers =
                sysUserMapper.selectAll();
        for (SysUser s:sysUsers
             ) {
            System.out.println(s);
        }
    }
}
