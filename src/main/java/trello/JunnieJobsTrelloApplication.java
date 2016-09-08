package trello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.thymeleaf.ThymeleafAutoConfiguration;
import org.springframework.context.annotation.Import;

import trello.config.Thymeleaf3AutoConfiguration;
//@EnableSwagger2

@Import(Thymeleaf3AutoConfiguration.class)
@SpringBootApplication(exclude = ThymeleafAutoConfiguration.class)
public class JunnieJobsTrelloApplication {

	public static void main(String[] args) {
		SpringApplication.run(JunnieJobsTrelloApplication.class, args);
		
		
	}

//	@Bean
//	public Docket api() {
//		return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.any())
//				.paths(PathSelectors.any()).build();
//	}

//
//	@Bean
//	@ConfigurationProperties("github.client")
//	OAuth2ProtectedResourceDetails github() {
//		return new AuthorizationCodeResourceDetails();
//	}
//
//	@Bean
//	@ConfigurationProperties("github.resource")
//	ResourceServerProperties githubResource() {
//		return new ResourceServerProperties();
//	}

}
