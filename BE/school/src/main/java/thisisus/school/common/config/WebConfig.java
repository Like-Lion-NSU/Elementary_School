package thisisus.school.common.config;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import lombok.RequiredArgsConstructor;
import thisisus.school.auth.controller.AuthenticatedMemberResolver;
import thisisus.school.auth.controller.interceptor.AuthenticationInterceptor;
import thisisus.school.auth.security.JwtTokenProvider;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {
	private final AuthenticationInterceptor authenticationInterceptor;
	private final JwtTokenProvider jwtTokenProvider;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(authenticationInterceptor)
			.addPathPatterns("/**")
			.excludePathPatterns("/swagger-ui/**", "/swagger-resources/**", "/v3/api-docs/**")
			.excludePathPatterns("/api/v1/oauth/authorize", "/api/v1/sign-up", "/api/v1/login", "/api/v1/refresh")
			.excludePathPatterns("/test/**");
	}

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
			.allowedMethods("GET", "POST", "PUT", "DELETE", "MESSAGE")
			.exposedHeaders("location")
			.allowedOrigins("*");
	}

	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
		resolvers.add(new AuthenticatedMemberResolver(jwtTokenProvider));
	}
}