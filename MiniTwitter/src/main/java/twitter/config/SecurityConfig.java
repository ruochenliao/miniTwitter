package twitter.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.web.authentication.rememberme.InMemoryTokenRepositoryImpl;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebMvcSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http 
			.formLogin()
				.loginPage("/login")
			.and()
				.logout()
					.invalidateHttpSession(true)
					.deleteCookies("loginTokenKey")
					.deleteCookies("JSESSIONID")
					.logoutSuccessUrl("/")
			.and()
				.authorizeRequests()
				.antMatchers("/").authenticated()
				.anyRequest().permitAll();;
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	//public void registerAuthentication(AuthenticationManagerBuilder auth) throws Exception {
		auth
			.inMemoryAuthentication()
				.withUser("john").password("password").roles("USER")
				.and()
				.withUser("liao").password("password").roles("USER")
				.and()
				.withUser("username").password("password").roles("USER");
	}
}
