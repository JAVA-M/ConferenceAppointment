package com.mxf.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Calendar;
import java.util.Map;

public class JWTUtils {
    // 加密密匙
    private static final String SIGNATURE = "%M$ZW#45{123OIzRq9wFJK1!3)L../`";

    /**
     * 生成 Token
     * @param map jwt的payload中携带的信息
     * @return
     */
    public static String getToken(Map<String, String> map) {
        JWTCreator.Builder builder = JWT.create();

        map.forEach(builder::withClaim);
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 7); // 生成的 token 有效期默认为 7天

        return builder.withExpiresAt(calendar.getTime())
                .sign(Algorithm.HMAC256(SIGNATURE));
    }

    /**
     * 验证 Token
     * @param token
     * @return
     */
    public static DecodedJWT verify(String token) {
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(SIGNATURE)).build();

        return jwtVerifier.verify(token);
    }

    public static Integer parseUserId(String token) {
        DecodedJWT verify = verify(token);
        return Integer.valueOf(verify.getClaim("id").asString());
    }

    public static String parseUserName(String token) {
        DecodedJWT verify = verify(token);
        return verify.getClaim("username").asString();
    }
}
