package neordinary.oteam.global.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.service.Server;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Configuration
@EnableOpenApi
public class SwaggerConfig {

    @Value("{swagger.url}")
    private String url;

    @Value("{swagger.desc}")
    private String desc;

    @Value("{spring.profiles.active}")
    private String profile;

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.OAS_30)
                .consumes(getConsumeContentTypes())
                .useDefaultResponseMessages(false)
                .securityContexts(List.of(securityContext()))
                .securitySchemes(List.of(apiKey()))
                .groupName("teamo-api")
                .servers(getServer(profile, url, desc))
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.ant("/api/**"))
                .build();
    }

    private Set<String> getConsumeContentTypes() {
        Set<String> consumes = new HashSet<>();
        consumes.add("application/json;charset=UTF-8");
        consumes.add("application/x-www-form-urlencoded");
        return consumes;
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(defaultAuth())
                .build();
    }

    private List<SecurityReference> defaultAuth() {
        final AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEveryThing");
        final AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return List.of(new SecurityReference("accessToken", authorizationScopes));
    }

    private ApiKey apiKey() {
        return new ApiKey("accessToken", "accessToken", "header");
    }

    private Server getServer(String profile, String url, String desc) {
        return new Server(profile, url, desc, Collections.emptyList(), Collections.emptyList());
    }

}

