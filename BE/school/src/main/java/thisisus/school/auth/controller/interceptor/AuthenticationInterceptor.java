package thisisus.school.auth.controller.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import thisisus.school.auth.config.AuthenticatedMemberId;
import thisisus.school.auth.config.AuthenticationExtractor;
import thisisus.school.auth.infrastructure.JwtTokenProvider;

@Component
@AllArgsConstructor
public class AuthenticationInterceptor implements HandlerInterceptor {

    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public boolean preHandle(final HttpServletRequest request, final HttpServletResponse response,
        final Object handler) {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        AuthenticatedMemberId AuthenticatedMemberId = handlerMethod.getMethodAnnotation(AuthenticatedMemberId.class);
        if (AuthenticatedMemberId == null) {
            return true;
        }

        final String token = AuthenticationExtractor.extractAccessToken(request);
        jwtTokenProvider.validateToken(token,"ACEESS_TOKEN");

        return true;
    }
}