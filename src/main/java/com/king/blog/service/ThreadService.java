package com.king.blog.service;

import com.king.blog.mapper.ArticleMapper;
import com.king.blog.pojo.Article;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class ThreadService {

    // 期望此操作在线程池中完成 不会影响原有的主线程
    @Async("taskExecutor") // 这个表示在线程池中完成
    public void updateArticleViewCount(ArticleMapper articleMapper, Article article) {
        // 获取 文章的浏览量  // 第一步：获取文章的浏览量
        Integer viewCounts = article.getViewCounts();
        // 获取文章的id
        Long id = article.getId();

        // 创建对象
        // 创建对象 让文章的浏览量进行加一
        Article articleUpdate = new Article();
        // 如果走到这里 就让浏览量加一
        articleUpdate.setViewCounts(viewCounts + 1);
        // 获取最新的文章浏览量
        Integer viewCounts1 = articleUpdate.getViewCounts();
        //     <!--用户浏览量 Integer updateArticle(Integer articleUpdate, Integer viewCounts, Long id);-->
        // 更新文章的浏览量
        articleMapper.updateArticle(viewCounts1,viewCounts,id);
//        try {
//            Thread.sleep(5000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

    }
}






























