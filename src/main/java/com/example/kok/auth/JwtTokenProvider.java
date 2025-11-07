package com.example.kok.auth;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;

import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtTokenProvider {
    @Value("${jwt.secret}")
    private String secretKey;
    private Key key;
    private final RedisTemplate<String, Object> redisTemplate;
    private final HttpServletResponse response;
    private final UserDetailsService userDetailService;

    private static final String REFRESH_TOKEN_PREFIX = "refresh:";
    private static final String BLACKLIST_PREFIX = "blacklist:";

    @PostConstruct
    protected void init() {
        byte[] keyBytes = Base64.getDecoder().decode(secretKey);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }


    public String createAccessToken(String username) {
        long ACCESS_TOKEN_VALIDITY = 1000L * 60 * 30;
        String accessToken = Jwts.builder()
                .setSubject(username)
                .claim("memberEmail", username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + ACCESS_TOKEN_VALIDITY))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();

        Cookie accessTokenCookie = new Cookie("accessToken", accessToken);
        accessTokenCookie.setHttpOnly(true);
        accessTokenCookie.setSecure(false);
        accessTokenCookie.setPath("/");
        accessTokenCookie.setMaxAge(60 * 10);
        response.addCookie(accessTokenCookie);

        return accessToken;
    }
    public String createAccessToken(String username ,String provider) {
        log.info("username:"+username+",provider:"+provider);
        long ACCESS_TOKEN_VALIDITY = 1000L * 60 * 30;
        String accessToken = Jwts.builder()
                .setSubject(username)
                .claim("userEmail", username)
                .claim("provider", provider)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + ACCESS_TOKEN_VALIDITY))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();

        Cookie accessTokenCookie = new Cookie("accessToken", accessToken);
        accessTokenCookie.setHttpOnly(true);
        accessTokenCookie.setSecure(false);
        accessTokenCookie.setPath("/");
        accessTokenCookie.setMaxAge(60 * 10);
        response.addCookie(accessTokenCookie);

        return accessToken;
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(key).build()
                    .parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }


    public Authentication getAuthentication(String token) {
        String username = getUserName(token);
        UserDetails userDetails = userDetailService.loadUserByUsername(username);
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }


    public String getUserName(String token) {
        return Jwts.parserBuilder().setSigningKey(key).build()
                .parseClaimsJws(token).getBody().getSubject();
    }


    public String createRefreshToken(String username) {
        long REFRESH_TOKEN_VALIDITY = 1000L * 60 * 60 * 24 * 1;
        log.info(username);
        log.info("리플레쉬 토큰 들어옴");
        String refreshToken = Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + REFRESH_TOKEN_VALIDITY))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();

        redisTemplate.opsForValue().set(
                REFRESH_TOKEN_PREFIX + username,
                refreshToken,
                REFRESH_TOKEN_VALIDITY,
                TimeUnit.MILLISECONDS
        );
        Cookie refreshTokenCookie = new Cookie("refreshToken", refreshToken);
        refreshTokenCookie.setHttpOnly(true);
        refreshTokenCookie.setSecure(false);
        refreshTokenCookie.setPath("/");
        refreshTokenCookie.setMaxAge(60 * 60 * 24 * 1); // 1일
        response.addCookie(refreshTokenCookie);

        return refreshToken;
    }
    public String createRefreshToken(String username ,String provider) {
        long REFRESH_TOKEN_VALIDITY = 1000L * 60 * 60 * 24 * 1;
        String refreshToken = Jwts.builder()
                .setSubject(username)
                .claim("provider",provider)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + REFRESH_TOKEN_VALIDITY))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();

        redisTemplate.opsForValue().set(
                REFRESH_TOKEN_PREFIX +provider +"_"+ username,
                refreshToken,
                REFRESH_TOKEN_VALIDITY,
                TimeUnit.MILLISECONDS
        );
        Cookie refreshTokenCookie = new Cookie("refreshToken", refreshToken);
        refreshTokenCookie.setHttpOnly(true);
        refreshTokenCookie.setSecure(false);
        refreshTokenCookie.setPath("/");
        refreshTokenCookie.setMaxAge(60 * 60 * 24 * 1); // 1일
        response.addCookie(refreshTokenCookie);

        return refreshToken;
    }
    //    쿠키와 Redis에서 각 RefreshToken을 가져와서 비교
    public boolean checkRefreshTokenBetweenCookieAndRedis(String username, String cookieRefreshToken){
        String redisRefreshToken = getRefreshToken(username);
        return redisRefreshToken.equals(redisRefreshToken);
    }

    public boolean checkRefreshTokenBetweenCookieAndRedis(String username, String provider, String cookieRefreshToken){
        String redisRefreshToken = getRefreshToken(username, provider);
        return redisRefreshToken.equals(redisRefreshToken);
    }

    public void deleteRefreshToken(String username) {
        redisTemplate.delete(REFRESH_TOKEN_PREFIX + username);
    }
    public void deleteRefreshToken(String username,String provider) {
        redisTemplate.delete(REFRESH_TOKEN_PREFIX +provider+"_"+ username);
    }
    public String getRefreshToken(String username) {
        return (String) redisTemplate.opsForValue().get(REFRESH_TOKEN_PREFIX + username);
    }

    public String getRefreshToken(String username, String provider) {
        return (String) redisTemplate.opsForValue().get(REFRESH_TOKEN_PREFIX + provider + "_" + username);
    }


    public boolean isRefreshTokenValid(String username, String token) {
        String redisRefreshToken = (String) redisTemplate.opsForValue().get(REFRESH_TOKEN_PREFIX + username);
        return token.equals(redisRefreshToken);
    }




    public Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }


    public String parseTokenFromHeader(HttpServletRequest request){
        String bearerToken = request.getHeader("Authorization");

        if(bearerToken != null && bearerToken.startsWith("Bearer ")){
            return bearerToken.substring(7);
        }

        if(request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                if("accessToken".equals(cookie.getName())){
                    return cookie.getValue();
                }
            }
        }

        return null;
    }



    public void addToBlacklist(String token){
        try {
            String tokenId = getBlackListTokenKey(token);
            Claims claims = getClaims(token);
            long gap = claims.getExpiration().getTime() - System.currentTimeMillis();
            if (gap > 0) {
                String blackListKey = BLACKLIST_PREFIX + tokenId;
                redisTemplate.opsForValue().set(
                        blackListKey,
                        "blacklisted",
                        gap,
                        TimeUnit.MILLISECONDS
                );
            }
        } catch (Exception e) {
            throw new RuntimeException("토큰을 블랙리스트에 추가하는데에 실패했습니다.");
        }

    }


    public boolean isTokenBlackList(String token){
        try {
            String tokenId = getBlackListTokenKey(token);
            String blacklistKey = BLACKLIST_PREFIX + tokenId;
            Boolean hasKey = redisTemplate.hasKey(blacklistKey);
            return hasKey;
        } catch (Exception e) {
            log.error("블랙리스트 확인 중 오류 발생: {}", e.getMessage());
            return true;
        }
    }


    private String getBlackListTokenKey(String token) {

        return DigestUtils.md5DigestAsHex(token.getBytes());
    }

    public boolean isTokenExpired(String token){
        try {
            Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
            Date expiration = claims.getExpiration();
            return expiration.before(new Date());
        } catch (ExpiredJwtException e){
            return true;
        } catch (Exception e){
            return true;
        }
    }
}