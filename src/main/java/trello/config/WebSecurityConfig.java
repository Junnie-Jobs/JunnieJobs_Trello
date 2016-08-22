package trello.config;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;



@Configuration
@EnableWebSecurity
@EnableOAuth2Sso
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Resource(name = "customUserDetailsService")
	private UserDetailsService customUserDetailsService;
	
	@Autowired 
	private ResourceServerProperties sso;
	
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	
    	http.csrf().disable();
    	http.headers().frameOptions().disable();
//    	http.antMatcher("/**").and().addFilterAfter(csrfHeaderFilter(), CsrfFilter.class);
    	
    	// static resources
    	http.authorizeRequests()
    	.antMatchers("/css/**", "/js/**", "/images/**", "/lib/**", "/fonts/**").permitAll();
    	
        http
            .authorizeRequests()
            	.antMatchers("/").permitAll()
            	.antMatchers("/servers/info").permitAll()
            	.antMatchers("/users/login").anonymous()
            	.antMatchers("/users/signUp").anonymous()
            	.anyRequest().authenticated()    	
                .and()
            .formLogin() //form 로그인 페이지는 아래의 경로로 이동한다.
                .loginPage("/users/login")
                .loginProcessingUrl("/users/login")
                .defaultSuccessUrl("/boards",true)
                .failureUrl("/users/login?error")
            	.usernameParameter("email")
				.passwordParameter("password")			
                .permitAll()
                .and()
            .logout()
            	.logoutSuccessUrl("/").permitAll();;
            	
            http.httpBasic();
        
         }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    	auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder());

    }
    
//    @Bean
//  	public ResourceServerTokenServices userInfoTokenServices() {
//  		return new CustomResourceServerTokenServices(sso.getUserInfoUri(), sso.getClientId());
//  	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder;
	}
	

}




