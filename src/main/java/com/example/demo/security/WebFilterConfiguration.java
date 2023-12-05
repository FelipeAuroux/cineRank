package com.example.demo.security;

import com.example.demo.domain.enums.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class WebFilterConfiguration {

	@Autowired
	private InterceptorFilter interceptorFilter;

	@Bean
	public CorsConfigurationSource corsConfigurationSource() {

		CorsConfiguration configuration = new CorsConfiguration();
		configuration.addAllowedOrigin("*"); // Permite todas as origens, você pode personalizar isso
		// Methods http authorized
		configuration.addAllowedMethod("GET");
		configuration.addAllowedMethod("POST");
		configuration.addAllowedMethod("PUT");
		configuration.addAllowedMethod("DELETE");
		configuration.addAllowedMethod("OPTIONS");
		// Headers http authorized
		configuration.addAllowedHeader("Authorization");
		configuration.addAllowedHeader("Content-Type");

		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);

		return source;


	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
	{
		http.cors(); // cross origin resource sharing (compartilhamento de recursos de origens cruzadas)

		http.csrf(AbstractHttpConfigurer::disable); // Habilita a segurança contra ataques csrf (Cross-site request forgery)

		http.formLogin(AbstractHttpConfigurer::disable); // Desabilita formulários de login html

		http.httpBasic(AbstractHttpConfigurer::disable);

		http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)); // Sem sessões

		http.authorizeHttpRequests((auth) -> auth
				// Login Controller
				.requestMatchers(HttpMethod.POST, "/login/*").permitAll()
//				// Avaliacao Controller
				.requestMatchers(HttpMethod.POST, "/avaliacao/novo").hasAuthority(Roles.ROLE_USER.name())
				.requestMatchers(HttpMethod.GET, "/avaliacao/listar-todas").hasAuthority(Roles.ROLE_USER.name())
				.requestMatchers(HttpMethod.DELETE, "/deletar/*").hasAuthority(Roles.ROLE_ADMIN.name())
				// Cinema Controller
				.requestMatchers(HttpMethod.POST, "/cinema/novo").hasAuthority(Roles.ROLE_ADMIN.name())
				.requestMatchers(HttpMethod.GET, "/cinema/listar-todos").hasAuthority(Roles.ROLE_USER.name())
				.requestMatchers(HttpMethod.DELETE, "/deletar-cinema/*").hasAuthority(Roles.ROLE_ADMIN.name())
				// Comentario Controller
				.requestMatchers(HttpMethod.POST, "/comentario/comentar").hasAuthority(Roles.ROLE_USER.name())
				.requestMatchers(HttpMethod.GET, "/comentario/listar-comentarios").hasAuthority(Roles.ROLE_USER.name())
				.requestMatchers(HttpMethod.DELETE, "/deletar-comentario/*").hasAuthority(Roles.ROLE_ADMIN.name())
				// Filme Controller
				.requestMatchers(HttpMethod.POST, "/filmes/novo").hasAuthority(Roles.ROLE_ADMIN.name())
				.requestMatchers(HttpMethod.GET, "/filmes/listar").hasAuthority(Roles.ROLE_USER.name())
				.requestMatchers(HttpMethod.DELETE, "/filmes/deletar/*").hasAuthority(Roles.ROLE_ADMIN.name())
				// Lista Controller
				.requestMatchers(HttpMethod.POST, "/lista/novo").hasAuthority(Roles.ROLE_USER.name())
				.requestMatchers(HttpMethod.GET, "/lista/listar/*").hasAuthority(Roles.ROLE_USER.name())
				// Sessao Controller
				.requestMatchers(HttpMethod.POST, "/sessao/novo").hasAuthority(Roles.ROLE_ADMIN.name())
				.requestMatchers(HttpMethod.GET, "/sessao/listar-todas").hasAuthority(Roles.ROLE_USER.name())
				.requestMatchers(HttpMethod.DELETE, "/sessao/deletar-sessao/*").hasAuthority(Roles.ROLE_ADMIN.name())
				// Usuario Controller
				.requestMatchers(HttpMethod.POST, "/usuario/novo").permitAll()
				.requestMatchers(HttpMethod.PUT, "/usuario/atualizar").hasAuthority(Roles.ROLE_ADMIN.name())
				.requestMatchers(HttpMethod.GET, "/usuario/buscar/*").hasAuthority(Roles.ROLE_ADMIN.name())
				.requestMatchers(HttpMethod.GET, "/usuario/listar-todos").hasAuthority(Roles.ROLE_ADMIN.name())
				.requestMatchers(HttpMethod.DELETE, "/usuario/deletar/*").hasAuthority(Roles.ROLE_ADMIN.name())
				// Swagger
				.requestMatchers("/swagger-ui/*").permitAll()
				.requestMatchers("/v3/api-docs").permitAll()
				.anyRequest().authenticated());

		http.addFilterBefore(this.interceptorFilter, UsernamePasswordAuthenticationFilter.class);

		return http.build();
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}

	@Bean
	public PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}


}
