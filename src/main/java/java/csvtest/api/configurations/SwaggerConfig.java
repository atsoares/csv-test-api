package java.csvtest.api.configurations;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Value("localhost:8080")
    private String baseUrl;

    @Value("CSV Reader Test API")
    private String apiTitle;

    @Value("CSV Reader API")
    private String apiInfo;

    @Bean
    public Docket microServicesApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .host(baseUrl)
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                .build()
                .useDefaultResponseMessages(false)
                .apiInfo(metaData(apiInfo));
    }

    private ApiInfo metaData(String description) {
        final String projectVersion = "1.0";
        return new ApiInfoBuilder()
                .description(description)
                .version(projectVersion)
                .title(apiTitle)
                .contact(
                        new Contact("code test csv-api", "https://github.com/atsoares", "aleexsoarees@gmail.com"))
                .license("free")
                .build();
    }

}