package com.zhzhgang.mall.sso.service.impl;

import com.zhzhgang.mall.common.pojo.MallResult;
import com.zhzhgang.mall.common.utils.JsonUtils;
import com.zhzhgang.mall.jedis.JedisClient;
import com.zhzhgang.mall.mapper.MallUserMapper;
import com.zhzhgang.mall.pojo.MallUser;
import com.zhzhgang.mall.pojo.MallUserExample;
import com.zhzhgang.mall.sso.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * 用户处理 Service
 *
 * @author zhzhgang
 * @create 2018-03-14 21:31
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private MallUserMapper userMapper;

    @Autowired
    private JedisClient jedisClient;

    @Value("${USER_SESSION}")
    private String userSession;

    @Value("${SESSION_EXPIRE}")
    private Integer sessionExpire;

    /**
     * 检查数据是否可用
     *
     * @param data
     * @param type
     * @return
     */
    @Override
    public MallResult checkData(String data, int type) {
        MallUserExample example = new MallUserExample();
        MallUserExample.Criteria criteria = example.createCriteria();

        if (type == 1) {
            criteria.andUsernameEqualTo(data);
        } else if (type == 2) {
            criteria.andPhoneEqualTo(data);
        } else if (type == 3) {
            criteria.andEmailEqualTo(data);
        } else {
            return MallResult.build(400, "非法数据");
        }

        List<MallUser> userList = userMapper.selectByExample(example);

        if (userList != null && userList.size() > 0) {
            return MallResult.ok(false);
        }

        return MallResult.ok(true);
    }

    /**
     * 注册
     *
     * @param user
     * @return
     */
    @Override
    public MallResult register(MallUser user) {
        // 检查数据有效性
        if (StringUtils.isBlank(user.getUsername())) {
            return MallResult.build(400, "用户名为空");
        }

        MallResult result = checkData(user.getUsername(), 1);
        if (!(boolean) result.getData()) {
            return MallResult.build(400, "用户名重复");
        }

        if (StringUtils.isBlank(user.getPassword())) {
            return MallResult.build(400, "密码为空");
        }

        if (StringUtils.isNotBlank(user.getPhone())) {
            result = checkData(user.getPhone(), 2);
            if (!(boolean) result.getData()) {
                return MallResult.build(400, "手机号码重复");
            }
        }

        if (StringUtils.isNotBlank(user.getEmail())) {
            result = checkData(user.getEmail(), 3);
            if (!(boolean) result.getData()) {
                return MallResult.build(400, "邮箱重复");
            }
        }
        // 补全 pojo 属性
        user.setCreated(new Date());
        user.setUpdated(new Date());

        // 对密码进行 MD5 加密
        String md5Password = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
        user.setPassword(md5Password);

        // 插入数据
        userMapper.insert(user);

        return MallResult.ok();
    }

    /**
     * 登录
     *
     * @param username
     * @param password
     * @return
     */
    @Override
    public MallResult login(String username, String password) {
        // 判断用户名和密码是否正确
        MallUserExample example = new MallUserExample();
        MallUserExample.Criteria criteria = example.createCriteria();
        criteria.andUsernameEqualTo(username);
        List<MallUser> userList = userMapper.selectByExample(example);
        if (userList == null && userList.size() > 0) {
            return MallResult.build(400, "用户名或密码不正确");
        }

        MallUser user = userList.get(0);
        if (!DigestUtils.md5DigestAsHex(password.getBytes()).equals(user.getPassword())) {
            return MallResult.build(400, "用户名或密码不正确");
        }
        // 生成 token，使用 UUID
        String token = UUID.randomUUID().toString();

        // 把用户信息保存到 Redis，key 就是 token，value 就是用户信息
        user.setPassword(null);
        jedisClient.set(userSession + ":" + token, JsonUtils.objectToJson(user));

        // 设置 key 的过期时间
        jedisClient.expire(userSession + ":" + token, sessionExpire);

        // 返回成功，要把 token 返回
        return MallResult.ok(token);
    }
}
