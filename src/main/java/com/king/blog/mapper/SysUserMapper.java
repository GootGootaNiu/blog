package com.king.blog.mapper;

import com.king.blog.pojo.Article;
import com.king.blog.pojo.SysUser;
import com.king.blog.vo.UserVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SysUserMapper {

    /**
     * 查询全部
     *
     * @return
     */
    List<SysUser> selectAll();

    /**
     * 根据用户的id 查询数据
     *
     * @param id 用户id
     * @return 返回受影响的行数authorId
     */
    SysUser findById(Long id);

    /**
     * 根据账户 和密码查询一条记录
     *
     * @param account  账户信息
     * @param password 密码
     * @return
     */
    SysUser fingUser(String account, String password);

    /**
     * 根据账户查询数据
     *
     * @param account
     * @return
     */
    SysUser findUserAccount(String account);

    /**
     * 根据 账户查询数据 返回一条记录
     *
     * @param account
     * @return
     */
    SysUser fingUserByAccount(String account);

    /**
     * 添加数据
     *
     * @param sysUser
     */
    void save(SysUser sysUser);

    /**
     * 根据
     * @param authorId
     * @return
     */
    SysUser findUserByAuthorId(@Param("id") Long authorId);
}
