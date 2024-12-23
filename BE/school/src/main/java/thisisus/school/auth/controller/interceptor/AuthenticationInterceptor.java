package thisisus.school.auth.controller.interceptor;

import static thisisus.school.member.domain.Role.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import lombok.AllArgsConstructor;
import thisisus.school.auth.config.Auth;
import thisisus.school.auth.dto.response.AuthInfoResponse;
import thisisus.school.auth.exception.AccessDeniedResourceException;
import thisisus.school.auth.security.AuthenticationExtractor;
import thisisus.school.auth.security.JwtTokenProvider;

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
		HandlerMethod handlerMethod = (HandlerMethod)handler;
		Auth auth = handlerMethod.getMethodAnnotation(Auth.class);
		if (auth == null) {
			return true;
		}

		final String token = AuthenticationExtractor.extractAccessToken(request);
		jwtTokenProvider.validateToken(token, "ACCESS_TOKEN");

		AuthInfoResponse authInfo = jwtTokenProvider.getAuthInfo(token);

		if(isAccessTeacher(auth)){
			if(isNotTeacher(authInfo.getRole())){
				throw new AccessDeniedResourceException();
			}
		}

		if (isAccessStudent(auth)) {
			if (isGuest(authInfo.getRole())) {
				throw new AccessDeniedResourceException();
			}
		}
		return true;
	}

	private boolean isAccessStudent(Auth auth) {
		return auth.role().compareTo(STUDENT) == 0;
	}

	private boolean isAccessTeacher(Auth auth){
		return auth.role().compareTo(TEACHER) == 0;
	}
}