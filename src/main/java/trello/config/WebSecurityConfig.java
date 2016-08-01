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
//            	.antMatchers("/").permitAll()
//            	.antMatchers("/users/**").anonymous()
            	.antMatchers("/**").permitAll()
//            	.antMatchers("/users/**").permitAll()
//            	.anyRequest().authenticated()
            	//signin 페이지를 permitAll() 로 주지 않고 anonymous() 주었다.
            	//이유는 permitAll()을 했을 경우 sign in 에 성공한 유저가 sign in form 페이지 URL 을 기억했다가 직접 access 할 경우 sign in form 페이지가 보이게 된다. 
            	//이렇게 되는 것을 원치 않고 단지 sign in form 페이지는 authentication 이 없는 사용자들, 즉 sign in을 아직 하지 않은 사용자들만 보이게 하기 위해서다. 
            	//그리고 나머지 모든 페이지에 대해서는 anyRequest().authenticated() 로 설정했다.           	
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





//@Autowired
//public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//  auth
//      .inMemoryAuthentication()
//          .withUser("user").password("password").roles("USER");
//}


//
//.authorizeRequests()
//.antMatchers("/js/**", "/css/**")
//.antMatcherS("/users/signUp**", "/users/login").permiAll();
//
//.antMatchers("/").access("hasRole('ROLE_USER')")
//.antMatchers("/b/**").acceess("hasRole('ROLE_USER')")
//
//.formLogin()
//	.loginPage("/users/login")
//	.loginProcessingUrl("/users/login");
//	.permitAll()
//	.and()


//.antMatchers("/boards/**").authenticated() // 이 url에 대해서는 권한을 요구 한다.
//.anyRequest().permitAll() // 나머지 url에 대해서는 permitAll
