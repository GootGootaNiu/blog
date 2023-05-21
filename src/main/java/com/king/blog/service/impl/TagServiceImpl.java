package com.king.blog.service.impl;

import com.king.blog.mapper.TagMapper;
import com.king.blog.pojo.Tag;
import com.king.blog.service.TagService;
import com.king.blog.vo.Result;
import com.king.blog.vo.TagVo;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;

import java.util.ArrayList;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TagServiceImpl implements TagService {
    @Autowired
    private TagMapper tagMapper;


    /**
     * 根据首页的id 查询首页的数据信息
     * @param articleId 首页的id
     * @return 把数据封装到vo类中返回
     */
    @Override
    public List<TagVo> findTagByArticleId(Long articleId) {
        // 多表查询数据
        List<Tag> tags = tagMapper.findTagsByArticleId(articleId);

        return copyList(tags);
    }

    /**
     * 1, 标签所拥有的文章数量最多 最热标签
     * 2, 查询 根据tag_id 分组 计数 从大到小 排列 取前 limit 个
     * @param limit
     * @return
     */
    @Override
    public Result hots(int limit) {
        List<Long> tagIds = tagMapper.findAllByTagIdLimIt(limit);
        if (CollectionUtils.isEmpty(tagIds)){
            return Result.success(Collections.emptyList());
        }

        List<Tag> allByTagIds = tagMapper.findAllByTagIds(tagIds);
        return Result.success(allByTagIds);
    }

    /**
     * 查询全部标签
     * @return
     */
    @Override
    public Result selectAll() {
        List<Tag> tags = tagMapper.selectAll();
        return Result.success(copyList(tags));
    }

    /**
     * 查询全部标签分类
     * @return
     */
    @Override
    public Result findAllDetail() {
        List<Tag> tags = tagMapper.selectAll();
        return Result.success(copyList(tags));
    }

    /**
     * 根据id 查询标签列表
     * @param id
     * @return
     */
    @Override
    public Result findAllDetailById(Long id) {
        Tag tag = tagMapper.findAllDetailById(id);
        return Result.success(copy(tag));
    }


    // 把查询出来的数据封装到Vo类中去
    public TagVo copy (Tag tag){
        TagVo tagVo = new TagVo();
        BeanUtils.copyProperties(tag,tagVo);
        return tagVo;
    }
    public List<TagVo> copyList(List<Tag> tagList){
        List<TagVo> tagVoList = new ArrayList<>();
        for (Tag tag : tagList){
            tagVoList.add(copy(tag));
        }
        return tagVoList;
    }
}



























