package com.imooc.power.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.imooc.power.entity.User;

/**
 * token 工具类
 *
 * @Author: Mr.Chen
 * @Version 1.0
 * @Date: 2019/6/28 22:51
 */
public class TokenUtil {

    /**
     * 根据登录的用户信息获得token
     *
     * @param user
     * @return
     */
    public static String getToken(User user) {
        String token = JWT.create().withAudience(user.getAccountId())
                .sign(Algorithm.HMAC256(user.getPassword()));
        return token;
    }
}
