package com.lin.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * jwt工具类
 * @author lzr
 * @date 2019-11-22 16:20:43
 */
public class JwtUtil {
    /**
     * SECRET_KEY 密钥
     */
    public static final String SECRET_KEY = "cat777";
    /**
     * TOKEN_EXPIRE_TIME token过期时间
     */
    public static final long TOKEN_EXPIRE_TIME = 12 * 60 * 60;
    /**
     * REFRESH_TOKEN_EXPIRE_TIME refreshToken过期时间
     */
    public static final long REFRESH_TOKEN_EXPIRE_TIME = 10 * 60 * 1000;
    /**
     * ISSUER 签发人
     */
    private static final String ISSUER = "lzr";

    /**
     * 生成token
     * @param username 用户身份
     * @return 返回token
     */
    public static String generateToken(String username){
        Date now = new Date();
        //算法
        Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);

        String token = JWT.create()
                .withIssuer(ISSUER)
                .withIssuedAt(now)
                .withExpiresAt(new Date(now.getTime() + TOKEN_EXPIRE_TIME))
                //保存身份标志
                .withClaim("username", username)
                .sign(algorithm);
        return token;
    }

    /**
     * 验证token
     */
    public static boolean verify(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer(ISSUER)
                    .build();
            verifier.verify(token);
            return true;
        } catch (Exception ex){
            ex.printStackTrace();
        }
        return false;
    }

    /**
     * 从token获取username
     */
    public static String getUsername(String token){
        try{
            return JWT.decode(token).getClaim("username").asString();
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return "";
    }
}
