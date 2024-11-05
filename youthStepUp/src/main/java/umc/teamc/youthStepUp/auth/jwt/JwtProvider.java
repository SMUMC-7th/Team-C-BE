package umc.teamc.youthStepUp.auth.jwt;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.time.Instant;
import java.util.Date;
import javax.crypto.SecretKey;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import umc.teamc.youthStepUp.auth.dto.LoginResponseDTO;
import umc.teamc.youthStepUp.auth.jwt.error.JwtErrorCode;
import umc.teamc.youthStepUp.auth.jwt.error.exception.JwtException;
import umc.teamc.youthStepUp.auth.service.CustomUserDetailService;

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

    public LoginResponseDTO getToken(Long id) {
        return new LoginResponseDTO(
                createAccessToken(id),
                accessExpiration,
                createRefreshToken(id),
                refreshExpiration
        );
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
        return Jwts.parser().verifyWith(key).build()
                .parseSignedClaims(token)
                .getPayload().get("id", String.class);
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

}
