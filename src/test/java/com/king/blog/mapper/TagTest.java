package com.king.blog.mapper;


import com.king.blog.pojo.Tag;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
public class TagTest {
    @Autowired
    private TagMapper tagMapper;

    @Test
    public void selectAll(){
        List<Tag> tags =
                tagMapper.selectAll();
        for (Tag tag : tags ){
            System.out.println(tag);
        }
    }


    @Test
    public void fingTagByArticle(){
        List<Tag> tagsByArticleId = tagMapper.findTagsByArticleId(1L);
        for (Tag tag:tagsByArticleId) {
            System.out.println(tag);
        }
    }
}
