package thisisus.school.config;

import com.fasterxml.classmate.TypeResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import thisisus.school.post.dto.PostDefaultResponseDto;

@Configuration
public class SwaggerConfig{

    @Bean
    public Docket api() {
        TypeResolver typeResolver = new TypeResolver();

        return new Docket(DocumentationType.OAS_30)
                .apiInfo(apiInfo())
                .additionalModels(
                        typeResolver.resolve(PostDefaultResponseDto.class)
                )
                .useDefaultResponseMessages(false)
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                .paths(PathSelectors.any())
                .build();
    }



    /**
     * api 정보 설정 부분
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Elementary-School API")
                .version("1.0.0")
                .build();
    }

}
