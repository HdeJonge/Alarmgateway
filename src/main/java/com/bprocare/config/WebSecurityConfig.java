package com.bprocare.config;

import org.springframework.context.annotation.Configuration;
/*
 * import
 * org.springframework.security.config.annotation.authentication.builders.
 * AuthenticationManagerBuilder; import
 * org.springframework.security.config.annotation.web.builders.HttpSecurity;
 * import
 * org.springframework.security.config.annotation.web.builders.WebSecurity;
 * import org.springframework.security.config.annotation.web.configuration.
 * EnableWebSecurity; import
 * org.springframework.security.config.annotation.web.configuration.
 * WebSecurityConfigurerAdapter; import
 * org.springframework.security.crypto.factory.PasswordEncoderFactories; import
 * org.springframework.security.crypto.password.PasswordEncoder;
 */

/*
 * @Configuration
 * 
 * @EnableWebSecurity public class WebSecurityConfig extends
 * WebSecurityConfigurerAdapter {
 * 
 * 
 * @Override protected void configure(final HttpSecurity http) throws Exception
 * { http.csrf() .disable() .authorizeRequests()
 * .antMatchers("/resources/**").permitAll() .anyRequest().authenticated()
 * .and() .formLogin() .loginPage("/login") .failureUrl("/login?error=true")
 * .permitAll();
 * 
 * }
 * 
 * @Override public void configure(WebSecurity web) throws Exception {
 * 
 * web.ignoring() .antMatchers("/resources/**", "/static/**", "/css/**",
 * "/js/**", "/images/**");
 * 
 * 
 * }
 * 
 * @Override protected void configure(AuthenticationManagerBuilder auth) throws
 * Exception { PasswordEncoder encoder =
 * PasswordEncoderFactories.createDelegatingPasswordEncoder();
 * auth.inMemoryAuthentication()
 * //.withUser("test").password("{noop}test").roles("USER");
 * .withUser("test").password(encoder.encode("test")).roles("USER");
 * 
 * } }
 */