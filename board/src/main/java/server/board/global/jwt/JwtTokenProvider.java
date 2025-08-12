package server.board.global.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import server.board.domain.user.entity.UserDetailsImpl;
import server.board.domain.user.service.UserDetailsServiceImpl;
import server.board.global.exception.error.CustomErrorCode;
import server.board.global.exception.error.RestApiException;

import java.security.Key;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Component
public class JwtTokenProvider {

    private long accessTokenValidMillisecond = 1000L * 60 * 60;// Valid 1 hour
    private long refreshTokenValidMillisecond = 6* 1000L * 60 * 60;// Valid 6 hour

    private final Key key;
    private final UserDetailsServiceImpl userDetailsService;

    public JwtTokenProvider(@Value("${spring.security.jwt.secret}") String secretKey, UserDetailsServiceImpl userDetailsService) {
        this.key = Keys.hmacShaKeyFor(secretKey.getBytes());
        this.userDetailsService = userDetailsService;
    }

    public JwtToken generateToken(Authentication authentication) {
        String authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        Date now = new Date();

        // Access Token 생성
        String accessToken = Jwts.builder()
                .setSubject(authentication.getName())
                .claim("auth", authorities)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + accessTokenValidMillisecond))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();

        // Refresh Token 생성
        String refreshToken = Jwts.builder()
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + refreshTokenValidMillisecond))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();

        // TokenInfo 객체 생성 및 반환
        return JwtToken.builder()
                .grantType("Bearer") // 토큰 타입 설정
                .accessToken(accessToken) // Access Token 설정
                .refreshToken(refreshToken) // Refresh Token 설정
                .build(); // TokenInfo 객체 생성
    }

    // JWT 토큰을 복호화하여 토큰에 들어있는 정보를 꺼내 Authentication 객체를 생성
    public Authentication getAuthentication(String token) {
        // Jwt 토큰 복호화
        Claims claims = getClaims(token);

        if (claims.get("auth") == null) {
            throw new RuntimeException("권한 정보가 없는 토큰입니다.");
        }

        Collection<? extends GrantedAuthority> authorities = Arrays.stream(claims.get("auth").toString().split(","))
                .map(SimpleGrantedAuthority::new)
                .toList();

        try{
            UserDetailsImpl userDetails = userDetailsService.loadUserByUsername(getEmailFromToken(token));
            return new UsernamePasswordAuthenticationToken(userDetails, "", authorities);
        }catch(BadCredentialsException | UsernameNotFoundException | RestApiException e) {
            throw new RestApiException(CustomErrorCode.USER_NOT_FOUND);
        }
    }

    public Claims getClaims(String accessToken){
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(accessToken)
                .getBody();
    }

    public String getEmailFromToken(String accessToken){
        return Jwts
                .parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(accessToken)
                .getBody()
                .getSubject();
    }

    // JWT 토큰의 유효성을 검증, 유효하지 않을 시 예외 처리
    public boolean validateToken(String token){
        if(token == null)
            throw new JwtException("AccessToken is null");

        try {
            Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            return true;
        } catch (ExpiredJwtException e) {
            throw new JwtException("토큰이 만료되었습니다.");
        } catch (MalformedJwtException e) {
            throw new JwtException("토큰의 형식이 옳바르지 않습니다.");
        } catch (SignatureException | SecurityException e) {
            throw new JwtException("토큰의 서명이 옳바르지 않습니다.");
        } catch (UnsupportedJwtException e) {
            throw new JwtException("토큰의 형식이 만료되었습니다. ");
        }
    }
}
