package de.michlb.sample.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.io.IOException;
import java.util.Properties;

/**
 * Konfigurationsklasse fuer Swagger RESTful API
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(SwaggerConfig.class);

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2) //
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("de.michlb.sample")) // reduce to my own api without spring and so on
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("security sample API")
                .description("")
                .version("0.0.1-SNAPSHOT")
                .build();
    }

    private String getWebServiceVersion() {
        final Properties properties = new Properties();
        try {
            properties.load(this.getClass().getClassLoader().getResourceAsStream("project.properties"));
        } catch (IOException e) {
            LOGGER.warn(e.getMessage(), e);
        }
        return properties.getProperty("version");
    }
}