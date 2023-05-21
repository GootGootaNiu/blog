package com.king.blog.mapper;


import com.king.blog.pojo.Article;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest // 表示该类是一个测试类
// @RunWith(SpringRunner.class) 表示启动这个单元测试类 需要传递一个参数 必须是SpringRunner的实列类型
public class ArticleTest {
    @Autowired
    private ArticleMapper articleMapper;

    @Test
    public void selectAll(){
        List<Article> articles =
                articleMapper.selectAll();
        for (Article alist: articles
             ) {
            System.out.println(alist);
        }
    }

    /**
     * 分页查询
     */
    @Test
    public void selectPage(){
        List<Article> articles = articleMapper.selectPage(1, 10);
        for (Article a :articles
             ) {
            System.out.println(a);
        }
    }

    @Test
    public void selectlimit(){
        List<Article> articles = articleMapper.selectsLimit(5);
        for (Article a:articles
             ) {
            System.out.println(a);
        }
    }

}
