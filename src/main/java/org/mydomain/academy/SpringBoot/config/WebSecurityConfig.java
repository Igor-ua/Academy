package org.mydomain.academy.SpringBoot.config;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableWebMvcSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	private DataSource datasource;

	@Autowired
	protected void setDatasource(DataSource datasource) {
		this.datasource = datasource;
	}
//    @Autowired
//    private HttpServletRequest httpServletRequest;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
				.authorizeRequests()
				.antMatchers("/").permitAll()
				.antMatchers("/img/*").permitAll()
				.antMatchers("/css/*").permitAll()
				.antMatchers("/js/*").permitAll()
				.antMatchers("/edit*").hasAuthority("ADMIN")
				.anyRequest().authenticated()
				.and()
				.formLogin()
				.loginPage("/login")
				.failureUrl("/login?error")
				.defaultSuccessUrl("/")
				.permitAll()
				.and()
				.logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				.deleteCookies("remember-me")
				.logoutSuccessUrl("/")
				.permitAll()
				.and()
				.csrf().disable()
				.rememberMe();
	}

//	@Autowired
//	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//		auth
//				.inMemoryAuthentication()
//				.withUser("user").password("123").roles("USER")
//				.and()
//				.withUser("admin").password("123").roles("ADMIN");
//	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		JdbcUserDetailsManager userDetailsService = new JdbcUserDetailsManager();
		userDetailsService.setDataSource(datasource);
		PasswordEncoder encoder = new BCryptPasswordEncoder();

		auth.userDetailsService(userDetailsService).passwordEncoder(encoder);
		auth.jdbcAuthentication().dataSource(datasource);

		if (!userDetailsService.userExists("user")) {
			List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
			authorities.add(new SimpleGrantedAuthority("USER"));
			User userDetails = new User("user", encoder.encode("123"), authorities);
			userDetailsService.createUser(userDetails);
		}

		if (!userDetailsService.userExists("admin")) {
			List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
			authorities.add(new SimpleGrantedAuthority("ADMIN"));
			User userDetails = new User("admin", encoder.encode("123"), authorities);
			userDetailsService.createUser(userDetails);
		}

		if (!userDetailsService.userExists("mike")) {
			List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
			authorities.add(new SimpleGrantedAuthority("ADMIN"));
			User userDetails = new User("mike", encoder.encode("123"), authorities);
			userDetailsService.createUser(userDetails);
		}
	}

}