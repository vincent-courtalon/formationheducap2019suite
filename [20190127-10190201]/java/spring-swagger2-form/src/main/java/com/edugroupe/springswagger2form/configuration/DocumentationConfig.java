package com.edugroupe.springswagger2form.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class DocumentationConfig {

	@Bean
	public Docket api() {
		// configuration de la generation de doc par swagger 2
		return new Docket(DocumentationType.SWAGGER_2)
						.useDefaultResponseMessages(false)
						.select()
						.apis(RequestHandlerSelectors
								.basePackage("com.edugroupe.springswagger2form.web"))
						.paths(PathSelectors.regex("/.*")) // comme any() pour l'instant
						.build()
						.apiInfo(apiInfos());
	}
	
	
	private ApiInfo apiInfos() {
		return new ApiInfoBuilder().title("Zoo manager Api")
								 .description("Rest Api to consult and update Zoo and animals")
								 .contact(new Contact("vincent courtalon", "http://courtalon.com", "vincent.courtalon@gmail.com"))
								 .license("(c) Omnicorp")
								 .licenseUrl("http://omnicorp.com")
								 .version("1.0.0")
								 .build();
	}
	
}
