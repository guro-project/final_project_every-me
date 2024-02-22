package com.everyme.global.security.common.utils;

import com.everyme.domain.user.entity.User;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class TokenUtils {

    private static String jwtSecretKey; // 토큰 키
    private static long tokenValidateTime; // 토큰 유효 기간

    @Value("sdfadsfsdfadsfasdfasdfasdfsdfnw4tn23n4on1k5n1k2n14369hn")
    public void setJwtSecretKey(String jwtSecretKey) {
        TokenUtils.jwtSecretKey = jwtSecretKey;
    }

    @Value("86400000")
    public void setTokenValidateTime(long tokenValidateTime) {
        TokenUtils.tokenValidateTime = tokenValidateTime;
    }

    // header의 token을 분리하는 메서드
    public static String splitHeader(String header) {
        if(!header.equals("")) {
            return header.split(" ")[1];
        } else {
            return null;
        }
    }

    // 유효한 토큰인지 확인하는 메소드
    public static boolean isValidToken(String token) {
        // 유효하지 않으면 복호화가 안됨
        try {
            Claims claims = getClaimsFormToken(token);
            return true;
        } catch (ExpiredJwtException e){
            e.printStackTrace();
            return false;
        }catch (JwtException e){
            e.printStackTrace();
            return false;
        }catch (NullPointerException e){
            e.printStackTrace();
            return false;
        }
    }

    // 토큰을 복호화하는 메소드
    public static Claims getClaimsFormToken(String token) {
        return Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(jwtSecretKey))
                .parseClaimsJws(token).getBody();
    }

    // 토큰을 생성하는 메소드
    public static String generateJwtToken(User user) {

        Date expireTime = new Date(System.currentTimeMillis()+tokenValidateTime);

        JwtBuilder builder = Jwts.builder() // 토큰 생성 라이브러리
                .setHeader(createHeader())
                .setClaims(createClaims(user))
                .setSubject("EveryMe token : " + user.getUserNo())
                .signWith(SignatureAlgorithm.HS256, createSignature())
                .setExpiration(expireTime);

        return builder.compact();
    }

    // 토큰의 header를 설정하는 메소드
    private static Map<String, Object> createHeader() {

        Map<String, Object> header = new HashMap<>();

        header.put("type", "jwt");
        header.put("alg", "HS256");
        header.put("date", System.currentTimeMillis());

        return header;
    }

    // 사용자 정보를 기반으로 Claim을 생성해주는 메소드
    private static Map<String, Object> createClaims(User user) {

        Map<String, Object> claims = new HashMap<>();

        claims.put("userId", user.getUserId());
        claims.put("Role", user.getRole());

        return claims;
    }

    // Jwt 서명을 발급해주는 메소드
    private static Key createSignature() {
        byte[] secretBytes = DatatypeConverter.parseBase64Binary(jwtSecretKey);
        return new SecretKeySpec(secretBytes, SignatureAlgorithm.HS256.getJcaName());
    }

}
