package com.king.blog.mapper;

import com.king.blog.pojo.ArticleBody;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class ArticleBodyTest {
    @Autowired
    private ArticleBodyMapper articleBodyMapper;

    @Test
    public void selectAll(){
        List<ArticleBody> articeBody =
                articleBodyMapper.selectAll();
        for (ArticleBody a :
                articeBody) {
            System.out.println(a);
        }
    }
}
