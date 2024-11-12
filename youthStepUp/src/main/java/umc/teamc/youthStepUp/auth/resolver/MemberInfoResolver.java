package umc.teamc.youthStepUp.auth.resolver;

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

@Component
@RequiredArgsConstructor
@Slf4j
public class MemberInfoResolver implements HandlerMethodArgumentResolver {
    private final JwtProvider jwtProvider;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        boolean hasAnnotation = parameter.hasParameterAnnotation(MemberInfo.class);
        boolean isAccountType = Long.class.isAssignableFrom(parameter.getParameterType());
        return hasAnnotation && isAccountType;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        String header = webRequest.getHeader(TokenConstant.HEADER_PREFIX.getValue());
        String token = jwtProvider.resolveToken(header);
        return Long.parseLong(jwtProvider.getUserId(token));
    }
}
