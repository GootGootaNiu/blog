package com.king.blog.mapper;

import com.king.blog.pojo.Category;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class CategoryTest {
    @Autowired
    private CategoryMapper categoryMapper;

    @Test
    public void selectAll(){
        List<Category> articeBody =
                categoryMapper.selectAll();
        for (Category a :
                articeBody) {
            System.out.println(a);
        }
    }
}
