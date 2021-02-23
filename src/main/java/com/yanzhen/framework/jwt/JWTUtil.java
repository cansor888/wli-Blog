package com.yanzhen.framework.jwt;

import com.yanzhen.entity.User;
import com.yanzhen.framework.exception.MyException;
import io.jsonwebtoken.*;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class JWTUtil {
    public static String token = "token";
    public static String jwt_secret = "yanzhen@cms@cc506183363.";
    public static long jwt_expr = 3600*24*1000;
    public static String sign(User user){
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        long nowMillis = System.currentTimeMillis();
        Date date = new Date(nowMillis);
        Map<String,Object> claims = new HashMap<>();
        claims.put("id",user.getId());
        claims.put("userName",user.getUserName());
        String subject = user.getUserName();
        JwtBuilder builder = Jwts.builder()
                .setClaims(claims)
                .setId(UUID.randomUUID().toString())
                .setIssuedAt(date)
                .setSubject(subject)
                .signWith(signatureAlgorithm,jwt_secret);
        Date exprDate = new Date(nowMillis+jwt_expr);
        builder.setExpiration(exprDate);
        return builder.compact();
    }
    public static boolean verify(String token){
        try {
            if(StringUtils.isEmpty(token)){
                return false;
            }
            Jwts.parser().setSigningKey(jwt_secret).parseClaimsJws(token).getBody();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public static User getUser(String token){
        try {
            if(StringUtils.isEmpty(token)){
                throw new MyException("token不能为空");
            }
            if(verify(token)){
                Claims claims = Jwts.parser().setSigningKey(jwt_secret).parseClaimsJws(token).getBody();
                User user = new User();
                user.setId(Integer.parseInt(claims.get("id")+""));
                user.setUserName(claims.get("userName")+"");
                return user;
            }else{
                throw new MyException("超时或不合法token");
            }
        } catch (Exception e) {
            throw new MyException("超时或不合法token");
        }
    }

}
