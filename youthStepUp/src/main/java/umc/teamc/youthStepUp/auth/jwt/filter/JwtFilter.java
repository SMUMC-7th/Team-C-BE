package umc.teamc.youthStepUp.auth.jwt.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import umc.teamc.youthStepUp.auth.jwt.JwtProvider;
import umc.teamc.youthStepUp.auth.jwt.error.JwtErrorCode;
import umc.teamc.youthStepUp.auth.jwt.error.exception.JwtException;

@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {
    private final JwtProvider jwtProvider;
    private final String HEADER_PREFIX = "Authorization";
    private final String HEADER_TYPE = "Bearer ";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String token = resolveToken(request);
        if (!jwtProvider.isExpired(token)) {
            setAuthentication(token);
        }
        filterChain.doFilter(request, response);
    }

    private void setAuthentication(String token) {
        Authentication authentication = jwtProvider.getAuthentication(token);
        SecurityContext context = SecurityContextHolder.getContext();
        context.setAuthentication(authentication);
    }

    private String resolveToken(HttpServletRequest request) {
        String header = request.getHeader(HEADER_PREFIX);
        if (StringUtils.hasText(header) && header.startsWith(HEADER_TYPE)) {
            return header.substring(7);
        }
        throw new JwtException(JwtErrorCode.TOKEN_INVALID);
    }
}
