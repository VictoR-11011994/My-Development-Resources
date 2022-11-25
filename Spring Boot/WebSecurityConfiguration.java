package com.example.demo.security;

@Configuration
@EnableWebSecurity // <-- this has already a: @Configuration ...so we can also skip this: -> @Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfiguration {
	
	
	// Example:
	@Autowired
	private AccountAuthenticationProvider accountAuthenticationProvider;
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
		authenticationManagerBuilder.authenticationProvider(accountAuthenticationProvider);
		
		http.csrf().disable() // WARNING: .disable() ok for Test. not for Prod.
		.authorizeRequests()
		.antMatchers(HttpMethod.POST, "/api/account/**").permitAll()
		.antMatchers("/**") // everything else
		.authenticated()
		.anyRequest().hasAnyRole("USER", "ADMIN")
		.and()
		.httpBasic(Customizer.withDefaults())
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		return http.build();
	}
	

// >> Deprecated	
// ... extends WebSecurityConfigurerAdapter
        @Override
	protected void configure(HttpSecurity http) throws Exception {
		//default
		//http.authorizeRequests().anyRequest().authenticated();
		//http.formLogin();
		//http.httpBasic();
		
		//customize
		http.authorizeRequests()
		.antMatchers("/private1").authenticated()
		.antMatchers("/private2").authenticated()
		.antMatchers("/public1").permitAll()
		.antMatchers("/public2").permitAll()
	    	.and().formLogin() // Browser display default form login
	    	.and().httpBasic(); // Any other app like: Postman
	}
	
	// configure users in memory
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
			.withUser("john").password("1234").authorities("admin")
			.and()
			.withUser("tony").password("12345").authorities("read")
			.and()
			.withUser("mike").password("123456").authorities("read")
			.and()
			.passwordEncoder(NoOpPasswordEncoder.getInstance());
	}

}
