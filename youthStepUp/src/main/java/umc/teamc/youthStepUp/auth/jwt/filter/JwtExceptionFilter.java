package umc.teamc.youthStepUp.auth.jwt.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import umc.teamc.youthStepUp.auth.jwt.error.JwtErrorCode;
import umc.teamc.youthStepUp.auth.jwt.error.exception.JwtException;
import umc.teamc.youthStepUp.auth.service.AuthService;
import umc.teamc.youthStepUp.global.apiPayload.CustomResponse;
import umc.teamc.youthStepUp.global.error.BaseErrorCode;

@RequiredArgsConstructor
@Component
public class JwtExceptionFilter extends OncePerRequestFilter {
    private final ObjectMapper objectMapper;
    private final AuthService authService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            filterChain.doFilter(request, response);
        } catch (JwtException exception) {
            BaseErrorCode code = exception.getCode();
//            if (code.getCode().equals(KakaoAuthErrorCode.TOKEN_EXPIRED.getCode())) {
//                if (getRefreshTokenAndReissue(request, response)) {
//                    return;
//                }
//            }
            setResponse(response, code);
        }
    }

    private boolean getRefreshTokenAndReissue(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String refreshToken = getRefreshTokenFromCookie(request);
        if (refreshToken == null) {
            setResponse(response, JwtErrorCode.REFRESH_TOKEN_MISSING);
            return true;
        }
        authService.reissueToken(refreshToken, response);
        return false;
    }

    private String getRefreshTokenFromCookie(HttpServletRequest request) {
        // 쿠키에서 리프레시 토큰 추출
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("refresh_token".equals(cookie.getName())) {
                    return cookie.getValue();  // 쿠키 값 반환
                }
            }
        }
        return null;  // 리프레시 토큰이 없으면 null 반환
    }

    private void setResponse(HttpServletResponse response, BaseErrorCode code) throws IOException {
        CustomResponse<?> customResponse = CustomResponse.fail(code);
        response.setStatus(code.getStatus().value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(objectMapper.writeValueAsString(customResponse));
    }

}
