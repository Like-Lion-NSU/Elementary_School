package thisisus.school.auth.controller;

import java.util.Objects;

import javax.servlet.http.HttpServletRequest;

import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import lombok.RequiredArgsConstructor;
import thisisus.school.auth.config.AuthenticatedMemberId;
import thisisus.school.auth.exception.InvalidTokenException;
import thisisus.school.auth.security.AuthenticationExtractor;
import thisisus.school.auth.security.JwtTokenProvider;

@Component
@RequiredArgsConstructor
public class AuthenticatedMemberResolver implements HandlerMethodArgumentResolver {

	private final JwtTokenProvider jwtTokenProvider;

	@Override
	public boolean supportsParameter(final MethodParameter parameter) {
		return parameter.hasParameterAnnotation(AuthenticatedMemberId.class);
	}

	@Override
	public Object resolveArgument(final MethodParameter parameter, final ModelAndViewContainer mavContainer,
		final NativeWebRequest webRequest, final WebDataBinderFactory binderFactory) {
		final HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
		final String token = AuthenticationExtractor.extractAccessToken(Objects.requireNonNull(request));
		if (token == null) {
			throw new InvalidTokenException();
		}
		jwtTokenProvider.validateToken(token, "ACCESS_TOKEN");
		return Long.valueOf(jwtTokenProvider.getMemberId(token));
	}
}