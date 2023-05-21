package com.king.blog.mapper;


import com.king.blog.pojo.Comment;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class CommentTest {
    @Autowired
    private CommentMapper commentMapper;

    @Test
    public void selectAll(){
        List<Comment> tags =
                commentMapper.selectAll();
        for (Comment tag : tags ){
            System.out.println(tag);
        }
    }
}
