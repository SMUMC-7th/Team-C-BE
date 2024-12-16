package umc.teamc.youthStepUp.auth.jwt.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import umc.teamc.youthStepUp.auth.constant.TokenConstant;
import umc.teamc.youthStepUp.auth.jwt.JwtProvider;
import umc.teamc.youthStepUp.auth.service.AuthService;

@RequiredArgsConstructor
@Component
public class JwtFilter extends OncePerRequestFilter {
    private final JwtProvider jwtProvider;
    private final AuthService authService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        Cookie[] cookies = request.getCookies();
        Cookie cookie = jwtProvider.findCookie(cookies, TokenConstant.ACCESS_TOKEN.getValue());
        if (cookie == null) {//비회원일때
            filterChain.doFilter(request, response);
            return;
        }
        String token = jwtProvider.resolveToken(cookie);
        Cookie RCookie = jwtProvider.findCookie(cookies, TokenConstant.REFRESH_TOKEN.getValue()); //refreshToken
        jwtProvider.isExpired(token);
        authService.setAuthentication(token);
        filterChain.doFilter(request, response);
    }
}
