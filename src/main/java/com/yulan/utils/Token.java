package com.yulan.utils;

import com.alibaba.fastjson.JSONObject;
import io.jsonwebtoken.*;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 蔡荣镔
 */
public class Token {

    /**
     * JWT的签发者
     */
    private static final String ISSURER="C.A.T 工作室";
    private static final Long CONTINUE_TIME=3600000L;
    private static Map<String,Object> header=new HashMap<String,Object>(2);
    private static final String KEY="aslkdjflaskjdfiozjxkvnwolketuo2i3u54r32094ufrjdzilkcjnazoise78u908q22ejhfkdlzncvo2w835r4yhnds";

    static{
        header.put("typ","JWT");
        header.put("alg","HS256");
    }

    /**
     * 生成token
     * @param object
     * @param continueTime
     * @return
     */
    public static String createToken(Object object,Long continueTime) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(KEY);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes,signatureAlgorithm.getJcaName());

        Object json = JSONObject.toJSON(object);
        System.out.println(json.toString());

        JwtBuilder builder = Jwts.builder().setId(object.hashCode()+"")
                .setIssuedAt(now)
                .setSubject(json.toString())
                .setIssuer(ISSURER)
                .signWith(signatureAlgorithm,signingKey);

        Long ttlMillis = continueTime;
        if(ttlMillis==null||ttlMillis <= 0) {
            ttlMillis = CONTINUE_TIME;
        }
        long expMillis = nowMillis + ttlMillis;
        Date exp = new Date(expMillis);
        builder.setExpiration(exp);
        return builder.compact();
    }

    /**
     * 对token进行转码
     * @param token
     * @return
     */
    public static Map<String,Object> parseToken(String token) {
        Map<String,Object> result = Response.getResponseMap(0,"",null);
        Claims claims = null;
        try {
            claims = Jwts.parser()
                    .setSigningKey(DatatypeConverter.parseBase64Binary(KEY))
                    .parseClaimsJws(token).getBody();
            String jsonString = claims.getSubject();
            Map<String,Object> datas = JSONObject.parseObject(jsonString);
            result.put("data",datas);
        } catch (ExpiredJwtException e) {
            return Response.getResponseMap(1,"token失效，请重新登录",null);
        }
        return result;
    }
}