package com.king.blog.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.king.blog.mapper.SysUserMapper;
import com.king.blog.pojo.SysUser;
import com.king.blog.service.LoginService;
import com.king.blog.service.SysUserService;
import com.king.blog.utils.JWTUtils;
import com.king.blog.vo.ErrorCode;
import com.king.blog.vo.Result;
import com.king.blog.vo.params.LoginParam;
import com.qiniu.util.Json;
import io.netty.util.internal.StringUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class LoginServiceImpl implements LoginService {
    /**
     * 这里使用到了SysUserService 类
     */
    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    // 定义一个静态属性
    private static final  String slat = "mszlu!@#";
    /**
     * 用户登录 业务处理
     *
     * @param loginParam 账户 和 密码
     * @return
     */
    @Override
    public Result login(LoginParam loginParam) {
        /**
         * 用户登录逻辑分析
         * 1,检查参数是否合法
         * 2,根据用户名和密码去user表中查询 是否存在
         * 3,如果不存在 登录失败
         * 4,如果存在使用jwt 生成token 返回给前端
         * 5,token放入redis
         * 当中 redis token:user信息
         * 设置过期时间(登录认证的时候先认证token 字符串是否合法 去redis 认证是否存在)         */
        // 第一步：拿到数据 检查数据是否合法 如果不合法就返回
        //用户账户
        String account = loginParam.getAccount();
        // 用户密码
        String password = loginParam.getPassword();
        // 判断账户和密码
        if (StringUtils.isBlank(account) || StringUtils.isBlank(password)){
            // 参数不合法 返回状态码
//            return Result.fail(10001,"参数不合法");
            return  Result.fail(ErrorCode.PARAMS_ERROR.getCode(),ErrorCode.PARAMS_ERROR.getMsg());
        }

        // 注意：添加一个数据 这里进行密码加密
        // 密码加上盐值
        password = DigestUtils.md5Hex(password + slat);

        // 第二步：根据账户和密码查询数据是否存在如果存在就返回数据
        SysUser sysUser = sysUserService.findUser(account,password);
//        SysUser sysUser = sysUserMapper.findUserAccount(account);
        if (sysUser == null){
            // 返回状态码
            return  Result.fail(ErrorCode.ACCOUNT_PWD_NOT_EXIST.getCode(),ErrorCode.ACCOUNT_PWD_NOT_EXIST.getMsg());
        }
        // 第三步: 使用jwt 认证
        String token = JWTUtils.createToken(sysUser.getId());
        // 把数据存入到 redis 中去
        // 注意 之类使用set 把数据存入进去 然后把sysUser 转换成JSON 格式
        // 转换之后设置过期时间 这里自己配置可以写到配置文件中去
        redisTemplate.opsForValue().set("TOKEN_"+ token, JSON.toJSONString(sysUser),1, TimeUnit.DAYS);
        // 返回Joke中的数据
        return Result.success(token);
    }

    /**
     * 根据token查询数据
     * @param token
     * @return
     */
    @Override
    public SysUser checkToke(String token) {
        if (StringUtils.isBlank(token)){
            return null;
        }
        // 获取 token 数据
        Map<String, Object> stringObjectMap = JWTUtils.checkToken(token);
        // 判断是否为空 如果为空直接返回
        if(stringObjectMap == null){
            return null;
        }
        // 获取redis 中的令牌
        String userJson = redisTemplate.opsForValue().get("TOKEN_" + token);
        // 判断
        if(StringUtils.isBlank(userJson)){
            return null;
        }
        SysUser sysUser = JSON.parseObject(userJson, SysUser.class);
        return sysUser;
    }

    /**
     * 退出登录
     * @param token
     * @return
     */
    @Override
    public Result logout(String token) {
        // 清空redis 中的数据
        redisTemplate.delete("TOKEN_"+token);
        return Result.success(null);
    }

    /**
     * 注册用户数据
     * @param loginParam 封装用户数据
     * @return
     */
    @Override
    public Result register(LoginParam loginParam) {
        // 第一步：拿到用户数据 判断是否为空
        String account = loginParam.getAccount();
        String password = loginParam.getPassword();
        String nickname = loginParam.getNickname();
        if (account == null || password == null || nickname == null){
            return Result.fail(ErrorCode.PARAMS_ERROR.getCode(), ErrorCode.PARAMS_ERROR.getMsg());
        }
        // 第二步：查询用户名是否重复
//        SysUser sysUser =  sysUserMapper.fingUserByAccount(account);
//        if (account.equals(sysUser.getAccount())){
//            return Result.fail(ErrorCode.ACCOUNT_EXIST.getCode(),"账户名称重复,请重新输入");
//        }
        SysUser sysUser =  sysUserMapper.fingUserByAccount(account);
        if (sysUser != null){
            return Result.fail(ErrorCode.ACCOUNT_EXIST.getCode(),"账户已经被注册了");
        }

        // 上面的判断没有错误
        // 这里就开始注册了
        sysUser = new SysUser();
        sysUser.setNickname(nickname);
        sysUser.setAccount(account);
        sysUser.setPassword(DigestUtils.md5Hex(password+slat));
        sysUser.setCreateDate(System.currentTimeMillis());
        sysUser.setLastLogin(System.currentTimeMillis());
        sysUser.setAvatar("/static/img/logo.b3a48c0.png");
        sysUser.setAdmin(1); //1 为true
        sysUser.setDeleted(0); // 0 为false
        sysUser.setSalt("");
        sysUser.setStatus("");
        sysUser.setEmail("");
        this.sysUserMapper.save(sysUser);
        // 向 Redis 中添加数据
        String token = JWTUtils.createToken(sysUser.getId());
        redisTemplate.opsForValue().set("TOKEN_"+ token, JSON.toJSONString(sysUser),1,TimeUnit.DAYS);
        return Result.success(token);
    }
}
























