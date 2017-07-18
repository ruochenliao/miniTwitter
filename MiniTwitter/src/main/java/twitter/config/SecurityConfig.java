package twitter.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.rememberme.InMemoryTokenRepositoryImpl;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import sun.security.provider.MD5;

@ComponentScan({"twitter.config"})
@Configuration
@EnableWebMvcSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private DataSource dataSource;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http 
			.formLogin()
				.loginPage("/login")
				.permitAll()
			.and()
				.logout()
					.invalidateHttpSession(true)
					.deleteCookies("loginTokenKey")
					.deleteCookies("JSESSIONID")
					.logoutSuccessUrl("/")
			.and()
				.authorizeRequests()
				.antMatchers("/logout","/register","/about").permitAll()
				.anyRequest().authenticated()
			.and()
				.csrf().disable();
        //http.addFilterBefore(customizeFilterSecurityInterceptor, FilterSecurityInterceptor.class)
        //.csrf().disable();
	}
	
	public String getUserQuery(){
		return "SELECT username, password, enabled from spitter WHERE username = ?";
	}
	
	@Autowired
	public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
		auth
			.jdbcAuthentication()
				.dataSource(dataSource)
				.usersByUsernameQuery( getUserQuery() );
	}	
	
	/*
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
			.inMemoryAuthentication()
				.withUser("john").password("password").roles("USER")
				.and()
				.withUser("liao").password("password").roles("USER")
				.and()
				.withUser("username").password("password").roles("USER");
		
	}
	*/
}
