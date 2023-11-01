package kr.co.rland.web.config.auth;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class RlandSecurityConfig {
	
	@Autowired
	private DataSource dataSource;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		//System.out.println(encoder.encode("111"));
		return encoder;
	}
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		
		http
			//.csrf(csrf->csrf.disable())
			.authorizeHttpRequests(
					authorize->authorize
					.requestMatchers("/admin/**")					
						.hasAnyRole("ADMIN")
					.requestMatchers("/member/**")					
						.hasAnyRole("ADMIN","MEMBER")
						.anyRequest().permitAll()
			)
			.formLogin(
					form->form
					.loginPage("/user/login")					
					.defaultSuccessUrl("/member/index"))
			.logout(
					logout->logout
					.logoutUrl("/user/logout")
					.logoutSuccessUrl("/index"));
		
		return http.build();
	}
	
	
	//@Bean
	public UserDetailsService jdbcUserDetailsService() {
		
		JdbcUserDetailsManager manager = new JdbcUserDetailsManager(dataSource);
		manager.setUsersByUsernameQuery("select user_name username, password, 1 enabled from member where user_name = ?");
//		-> 결과 집합의 모양
//		   ┌────────────┬───────────┬─────────┐
//		   │  username  │  password │ enabled │
//         ├────────────┼───────────┼─────────┤
//		   │   newlec   │    111    │    1    │		
		
		//manager.setAuthoritiesByUsernameQuery("select user_name username, 'ROLE_ADMIN' authority from member where user_name = ?");
		String sqlForAuthorties = "select m.user_name username, r.name authority " + 
		"from member_role mr left join member m on mr.member_id = m.id "+  
		"					left join role r on mr.role_id = r.id "+
		"where m.user_name=?";
		manager.setAuthoritiesByUsernameQuery(sqlForAuthorties);
//		-> 결과 집합의 모양
//		   ┌────────────┬──────────────┐
//		   │  username  │   authority  │
//		   ├────────────┼──────────────┤
//		   │   newlec   │ ROLE_ADMIN   │
//		   │   newlec   │ ROLE_MEMBER  │
		
		
		return manager;
	}
	
	//@Bean
	public UserDetailsService userDetailsService() {
		UserDetails user1 = User
								.builder()
								.username("newlec")
								.password("{noop}111")
								.roles("MEMBER", "ADMIN")
								.build();
		UserDetails user2 = User
								.builder()
								.username("dragon")
								.password("111")
								.roles("MEMBER")
								.build();
		
		return new InMemoryUserDetailsManager(user1, user2);
	}
	
	
}



