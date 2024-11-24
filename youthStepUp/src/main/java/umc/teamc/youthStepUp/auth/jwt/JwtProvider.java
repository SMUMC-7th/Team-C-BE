package umc.teamc.youthStepUp.auth.jwt;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.Cookie;
import java.time.Instant;
import java.util.Date;
import javax.crypto.SecretKey;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseCookie;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import umc.teamc.youthStepUp.auth.constant.TokenConstant;
import umc.teamc.youthStepUp.auth.jwt.error.JwtErrorCode;
import umc.teamc.youthStepUp.auth.jwt.error.exception.JwtException;
import umc.teamc.youthStepUp.auth.service.CustomUserDetailService;
import umc.teamc.youthStepUp.global.error.exception.CustomException;

@Slf4j
@Component
public class JwtProvider {
    private final CustomUserDetailService userDetailService;
    private final SecretKey key;
    private final long accessExpiration;
    private final long refreshExpiration;

    public JwtProvider(@Value("${Jwt.secret}") String secretKey,
                       @Value("${Jwt.token.access-expiration-time}") long accessExpiration,
                       @Value("${Jwt.token.refresh-expiration-time}") long refreshExpiration,
                       CustomUserDetailService customUserDetailService) {
        this.key = getSigningKey(secretKey);
        this.accessExpiration = accessExpiration;
        this.refreshExpiration = refreshExpiration;
        this.userDetailService = customUserDetailService;
    }

    private SecretKey getSigningKey(String secretKey) {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey));
    }

    public String createAccessToken(Long id) {
        return createToken(id, this.accessExpiration);
    }

    public String createRefreshToken(Long id) {
        return createToken(id, this.refreshExpiration);
    }

    public Authentication getAuthentication(String token) {
        String id = getUserId(token);
        UserDetails userDetails = userDetailService.loadUserByUsername(id);
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    private String createToken(Long id, long expiration) {
        Instant issuedAt = Instant.now();
        Instant expiredAt = issuedAt.plusMillis(expiration);
        return Jwts.builder()
                .claim("role", "ROLE_USER")
                .claim("id", id)
                .issuedAt(Date.from(issuedAt))
                .expiration(Date.from(expiredAt))
                .signWith(key)
                .compact();
    }

    public String resolveToken(Cookie cookie) {
        String token = null;
        if (TokenConstant.ACCESS_TOKEN.getValue().equals(cookie.getName())) {
            token = cookie.getValue();
        }
        return token;
    }

    public Long getExpiredIn(String token) {
        return (Jwts.parser().verifyWith(key).build().parseSignedClaims(token)
                .getPayload()
                .getExpiration().getTime());
    }

    public String getRole(String token) {
        return (Jwts.parser().verifyWith(key).build().parseSignedClaims(token)
                .getPayload()
                .get("role", String.class));
    }

    public String getUserId(String token) {
        return String.valueOf(Jwts.parser().verifyWith(key).build()
                .parseSignedClaims(token)
                .getPayload().get("id"));
    }

    public Boolean isExpired(String token) {
        try {
            return Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload().getExpiration()
                    .before(new Date());
        } catch (SecurityException | MalformedJwtException | UnsupportedJwtException | IllegalArgumentException e) {
            throw new JwtException(JwtErrorCode.TOKEN_INVALID);
        } catch (ExpiredJwtException e) {
            throw new JwtException(JwtErrorCode.TOKEN_EXPIRED);
        }
    }

    public Cookie findCookie(Cookie[] cookies, String type) {
        Cookie findCookie = null;
        try {
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (type.equals(cookie.getName())) {
                        findCookie = cookie;
                        break;
                    }
                }
            }
            return findCookie;
        } catch (Exception e) {
            throw new CustomException(JwtErrorCode.HEADER_NO_TOKEN);
        }
    }

    public ResponseCookie createRefreshCookie(Long id) {
        String name = "refreshToken";
        String value = createRefreshToken(id);
//        Cookie cookie = new Cookie(cookieName, cookieValue);
//        cookie.setHttpOnly(true);
//        cookie.setSecure(true);
//        cookie.setPath("/");
//        cookie.setMaxAge(60 * 60 * 24);
        return ResponseCookie.from(name, value)
                .path("/")
                .sameSite("None")
                .httpOnly(true)
                .secure(true)
                .maxAge(refreshExpiration)
                .build();
    }

    public ResponseCookie createAccessCookie(Long id) {
        String name = "accessToken";
        String value = createAccessToken(id);
        return ResponseCookie.from(name, value)
                .path("/")
                .sameSite("None")
                .httpOnly(true)
                .secure(true)
                .maxAge(accessExpiration)
                .build();
        //        Cookie cookie = new Cookie(cookieName, cookieValue);
//        cookie.setHttpOnly(true);
//        cookie.setSecure(true);
//        cookie.setPath("/");
//        cookie.setMaxAge(60 * 5);
    }

}
