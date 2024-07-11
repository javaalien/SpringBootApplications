package com.oauth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.oauth2.client.CommonOAuth2Provider;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	public ClientRegistrationRepository clientRepository() {
		ClientRegistration githubRegistration = CommonOAuth2Provider.GITHUB.getBuilder("github").clientId("id")
				.clientSecret("secret").build();

		ClientRegistration facebookregistration = CommonOAuth2Provider.FACEBOOK.getBuilder("facebook").clientId("id")
				.clientSecret("secret").build();

		return new InMemoryClientRegistrationRepository(githubRegistration, facebookregistration);
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(a -> a.requestMatchers("/**login**", "/error", "/webjars/**", "/templates/**")
				.permitAll().anyRequest().authenticated())
				.exceptionHandling(ex -> ex.authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/login")))
				.csrf(csrf -> csrf.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))
				.logout(logout -> logout.logoutSuccessUrl("/login").permitAll())
				.oauth2Login(oauth2 -> oauth2.loginPage("/login"));

		return http.build();
	}
}
