package umc.teamc.youthStepUp.auth.resolver;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import umc.teamc.youthStepUp.auth.annotation.MemberInfo;
import umc.teamc.youthStepUp.auth.constant.TokenConstant;
import umc.teamc.youthStepUp.auth.jwt.JwtProvider;
import umc.teamc.youthStepUp.auth.jwt.error.JwtErrorCode;
import umc.teamc.youthStepUp.auth.jwt.error.exception.JwtException;
import umc.teamc.youthStepUp.member.entity.Member;
import umc.teamc.youthStepUp.member.error.MemberErrorCode;
import umc.teamc.youthStepUp.member.error.exception.MemberCustomException;
import umc.teamc.youthStepUp.member.repository.MemberRepository;

@Component
@RequiredArgsConstructor
@Slf4j
public class MemberInfoResolver implements HandlerMethodArgumentResolver {
    private final JwtProvider jwtProvider;
    private final MemberRepository memberRepository;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        boolean hasAnnotation = parameter.hasParameterAnnotation(MemberInfo.class);
        boolean isAccountType = Member.class.isAssignableFrom(parameter.getParameterType());
        return hasAnnotation && isAccountType;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        try {
            Cookie[] cookies = webRequest.getNativeRequest(HttpServletRequest.class).getCookies();
            Cookie cookie = jwtProvider.findCookie(cookies, TokenConstant.ACCESS_TOKEN.getValue());
            String token = jwtProvider.resolveToken(cookie);
            Long id = Long.parseLong(jwtProvider.getUserId(token));
            return memberRepository.findById(id).orElseThrow(
                    () -> new MemberCustomException(MemberErrorCode.MEMBER_NOT_FOUND)
            );
        } catch (Exception e) {
            throw new JwtException(JwtErrorCode.HEADER_NO_TOKEN);
        }
    }
}
