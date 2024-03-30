package thisisus.school.common.config;

import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import java.util.List;
import javax.servlet.ServletContext;
import org.springdoc.core.SpringDocUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import thisisus.school.auth.config.AuthenticatedMemberId;

@Configuration
public class SwaggerConfig{

    @Bean
    public OpenAPI openAPI(ServletContext servletContext) {
        String contextPath = servletContext.getContextPath();
        Server server = new Server().url(contextPath);
        return new OpenAPI()
            .servers(List.of(server))
            .addSecurityItem(securityItem())
            .components(new Components()
                .addSecuritySchemes("Authorization(accessToken)", securityScheme()));
    }

    private SecurityScheme securityScheme() {
        return new SecurityScheme()
            .type(SecurityScheme.Type.HTTP)
            .scheme("bearer")
            .bearerFormat("JWT")
            .in(SecurityScheme.In.HEADER)
            .name(HttpHeaders.AUTHORIZATION);
    }

    private SecurityRequirement securityItem(){
        SecurityRequirement securityItem = new SecurityRequirement();
        securityItem.addList("Authorization(accessToken)");
        return securityItem;
    }

    static {
        SpringDocUtils.getConfig().addAnnotationsToIgnore(AuthenticatedMemberId.class);
    }
}
