package com.king.blog.service.impl;

import com.king.blog.mapper.CommentMapper;
import com.king.blog.pojo.Comment;
import com.king.blog.pojo.SysUser;
import com.king.blog.service.CommentService;
import com.king.blog.service.SysUserService;
import com.king.blog.utils.UserThreadLocal;
import com.king.blog.vo.CommentParam;
import com.king.blog.vo.CommentVo;
import com.king.blog.vo.Result;
import com.king.blog.vo.UserVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private SysUserService sysUserService;


    /**
     * 根据文章id 查询所有的评论列表
     *
     * @param id
     * @return
     */
    @Override
    public Result commentById(Long id) {
        /**
         * 业务分析
         * 1, 根据id文章 id查询评论列表 从comment 表中查询
         * 2, 根据作者的id 查询作者的信息
         * 3, 判断 如果level = 1 要去查询他有没有子评论
         * 4, 如果有 根据评论的id进行查询(parent_id)
         */
        List<Comment> comments = this.commentMapper.selectById(id);
        System.out.println(comments);

        List<CommentVo> commentVoList = copyList(comments);
        return Result.success(commentVoList);
    }

    /**
     * 用户品论
     * @param commentParam 前端传过来的数据
     * @return
     */
    @Override
    public Result comment(CommentParam commentParam) {

        // 第一步：从登录中获取用户数据 只用登录了之后才能去访问
        SysUser sysUser = UserThreadLocal.get();
        // 第二步: 创建对象然后 向对象中添加数据
        Comment comment = new Comment();
        comment.setArticleId(commentParam.getArticleId()); // // 文章id
        comment.setAuthorId(sysUser.getId()); // 被评论的用户id
        comment.setContent(commentParam.getContent());// 评论内容
        // 更新时间
        comment.setCreateDate(System.currentTimeMillis());

        // 拿到父评论的id 拿到之后进行判断
        Long parent = commentParam.getParent();
        // 如果为空 null 就表示以一级标签 如果不是就是二级标签
        if(parent == null || parent == 0){
            // 向 level 中赋值
            comment.setLevel(1);
        }else {
            comment.setLevel(2);
        }
        comment.setParentId(parent == null ? 0 : parent);
        Long toUserId = commentParam.getToUserId();
        comment.setToUid(toUserId == null ? 0 : toUserId);
        commentMapper.insert(comment);
        return Result.success(null);
    }

    /**
     * 把数据封装到Vo类中去
     *
     * @param comments 文章数据
     * @return
     */
    private List<CommentVo> copyList(List<Comment> comments) {
        List<CommentVo> commentVoList = new ArrayList<>();
        for (Comment comment : comments) {
            commentVoList.add(copy(comment));
        }
        return commentVoList;
    }

    private CommentVo copy(Comment comment) {
        // 创建文章的Vo 对象 把数据拷贝到里面
        CommentVo commentVo = new CommentVo();
        BeanUtils.copyProperties(comment, commentVo);
        // 作者信息
        // 查询作者信息
        // 先获取作者信息
        Long authorId = comment.getAuthorId();
        // 根据作者信息查询数据
        // 作者的vo对象
        UserVo userVo = this.sysUserService.findUserByUserById(authorId);
        commentVo.setAuthor(userVo);

        // 子评论
        Integer level = comment.getLevel();
        // 判断 level 是否等于1
        if (1 == level) {
            Long id = comment.getId();
            //查询子评论
            List<CommentVo> commentVoList = findCommentsByParentId(id);
            // commentVo 拿到数据
            commentVo.setChildrens(commentVoList);
        }
        // to User 给谁评论
        if (level > 1) {
            Long toUid = comment.getToUid();
            UserVo toUservo = this.sysUserService.findUserByUserById(toUid);
            commentVo.setToUser(toUservo);
        }
        return commentVo;
    }


    /**
     * 根据id查询的子信息
     *
     * @param id
     * @return
     */
    private List<CommentVo> findCommentsByParentId(Long id) {
        return copyList(this.commentMapper.selectByIds(id));
    }
}























