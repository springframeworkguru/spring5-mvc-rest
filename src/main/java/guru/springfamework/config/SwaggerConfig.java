package guru.springfamework.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

/**
 * Darcy Xian  25/11/20  9:58 am      spring5-mvc-rest
 */
@EnableSwagger2
@Configuration

public class SwaggerConfig extends WebMvcConfigurationSupport {
    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select().apis(RequestHandlerSelectors.basePackage("guru.springfamework.controllers.v1"))
                .paths(PathSelectors.any())
                .build()
                .pathMapping("/")
                .apiInfo(metaData());
    }
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
   }
   private ApiInfo metaData(){
        Contact contact = new Contact("Darcy Xian","http","asdfadf@gmail.com");

                return new ApiInfo(
                        "Spring Framework Darcy",
                        "Spring Framework 5",
                        "1.0",
                        "terms of service: blah",
                        contact,
                        "Apache License Version 2.0",
                        "https://www.apache.org/licenses/LICENSE-2.0",
                        new ArrayList<>());


   }

}
