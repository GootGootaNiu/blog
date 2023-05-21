package com.king.blog.service.impl;

import com.king.blog.mapper.ArticleBodyMapper;
import com.king.blog.pojo.ArticleBody;
import com.king.blog.service.ArticleBodyService;
import com.king.blog.vo.ArticleBodyVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticleBodyServiceImpl implements ArticleBodyService {

    @Autowired
    private ArticleBodyMapper articleBodyMapper;

    /**
     * 根据 bodyId 查询数据
     * @param bodyId
     * @return
     */
    @Override
    public ArticleBodyVo selectBodyId(Long bodyId) {
        // 分析 根据id 查询数据
        // 然后再把数据放到ArticleBodyVo 类中去
        ArticleBody articleBody = articleBodyMapper.selectBodyId(bodyId);
        ArticleBodyVo articleBodyVo = new ArticleBodyVo();
        articleBodyVo.setContent((articleBody.getContent()));
        return articleBodyVo;
    }
}























