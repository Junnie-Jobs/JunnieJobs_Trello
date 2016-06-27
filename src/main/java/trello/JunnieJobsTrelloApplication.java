package trello;

import org.h2.server.web.WebServlet;
import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.autoconfigure.thymeleaf.ThymeleafAutoConfiguration;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import trello.config.Thymeleaf3AutoConfiguration;

@EnableConfigurationProperties(DataSourceProperties.class)
@Import(Thymeleaf3AutoConfiguration.class)
@SpringBootApplication(exclude = ThymeleafAutoConfiguration.class)
public class JunnieJobsTrelloApplication {

	public static void main(String[] args) {
		SpringApplication.run(JunnieJobsTrelloApplication.class, args);
	}
	
	@Bean
	public ServletRegistrationBean h2servletRegistration() {
		ServletRegistrationBean registration = new ServletRegistrationBean(new WebServlet());
		registration.addUrlMappings("/console/*");
		return registration;
	}
}
