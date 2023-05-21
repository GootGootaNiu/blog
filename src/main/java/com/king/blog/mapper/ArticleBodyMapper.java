package com.king.blog.mapper;

import com.king.blog.pojo.ArticleBody;
import org.apache.catalina.LifecycleState;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Mapper
public interface ArticleBodyMapper {

    /**
     * 查询全部数据
     * @return
     */
    List<ArticleBody> selectAll();

    /**
     * 根据用户的body id查询数据
     * @param bodyId
     * @return
     */
    ArticleBody selectBodyId(@Param("id") Long bodyId);

    /**
     * 插入文章
     * @param articleBody
     */
    void insert(ArticleBody articleBody);
}
