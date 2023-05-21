package com.king.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.king.blog.mapper.SysUserMapper;
import com.king.blog.pojo.SysUser;
import com.king.blog.service.LoginService;
import com.king.blog.service.SysUserService;
import com.king.blog.vo.ErrorCode;
import com.king.blog.vo.LoginUserVo;
import com.king.blog.vo.Result;
import com.king.blog.vo.UserVo;
import org.apache.catalina.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private LoginService loginService;


    @Override
    public SysUser fingUserById(Long id) {
        SysUser sysUser = sysUserMapper.findById(id);
        if (sysUser == null){
            sysUser = new SysUser();
            sysUser.setNickname("码神之路");

        }
        return sysUser;
    }

    /**
     * 根据账户和密码查询一条数据返回记录
     * @param account 账户
     * @param password 密码
     * @return
     */
    @Override
    public SysUser findUser(String account, String password) {
        // 第一步查询数据判断一下

//
//        LambdaQueryWrapper<SysUser> queryWrapper = new LambdaQueryWrapper<>();
//        queryWrapper.eq(SysUser::getAccount,account);
//        queryWrapper.eq(SysUser::getPassword,password);
//        queryWrapper.select(SysUser::getAccount,SysUser::getId,SysUser::getAvatar,SysUser::getNickname);
//        queryWrapper.last("limit 1");
        //return sysUserMapper.selectOne(queryWrapper);
        return sysUserMapper.fingUser(account, password);
    }

    /**
     * token 这个数据在令牌中去获取
     *  1,token 哈法性效验
     *  是否为空 解析是否成功 redis 是否存在
     *  2, 如果效验失败 返回错误
     *  3, 如果成功 返回对应的结果 loginUVo
     * @param token token 查询数据
     * @return
     */
    @Override
    public Result findUserByToken(String token) {
        SysUser sysUser = loginService.checkToke(token);
        if (sysUser == null){
            // 如果token 没有数据就返回状态码
            Result.fail(ErrorCode.TOKEN_ERROR.getCode(), ErrorCode.TOKEN_ERROR.getMsg());
        }
        // 把数据放到vo类中 然后再把拿到的数据返回
        LoginUserVo loginUserVo = new LoginUserVo();
        loginUserVo.setId(sysUser.getId());
        loginUserVo.setNickname(sysUser.getNickname());
        loginUserVo.setAccount(sysUser.getAccount());
        loginUserVo.setAvatar(sysUser.getAvatar());
        return Result.success(loginUserVo);
    }

    /**
     * 查询作者的数据
     * @param authorId
     * @return
     */
    @Override
    public UserVo findUserByUserById(Long authorId) {
        // 根据作者id 查询数据
        SysUser sysUser = this.sysUserMapper.findUserByAuthorId(authorId);
        // 第二步：判断查询的数据是否有值
        if(sysUser == null){
            //如果没有数据就添加
            sysUser = new SysUser();
            sysUser.setId(1L);
            sysUser.setAvatar("/static/img/logo.b3a48c0.png");
            sysUser.setNickname("小阿牛");
        }
        // 创建UserVo 对象把数据封装一下
        UserVo userVo =new UserVo();
        // 复制
        BeanUtils.copyProperties(sysUser,userVo);
        return userVo;

    }
}





















