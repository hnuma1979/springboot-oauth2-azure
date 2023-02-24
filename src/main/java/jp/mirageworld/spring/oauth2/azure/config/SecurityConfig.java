package jp.mirageworld.spring.oauth2.azure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer.AuthorizationManagerRequestMatcherRegistry;
import org.springframework.security.config.annotation.web.configurers.CorsConfigurer;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer;
import org.springframework.security.config.annotation.web.configurers.HttpBasicConfigurer;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.config.annotation.web.configurers.oauth2.client.OAuth2LoginConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(this.authorizeHttpRequestsCustomizer())
                .httpBasic(this.httpBasicCustomizer())
                .formLogin(this.formLoginCustomizer())
                .oauth2Login(this.oauth2LoginCustomizer())
                .logout(this.logoutCustomizer())
                .csrf(this.csrfCustomizer())
                .cors(this.corsCustomizer())
                .build();
    }

    /**
     * @see {@link AuthorizationManagerRequestMatcherRegistry}
     */
    Customizer<AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry> authorizeHttpRequestsCustomizer() {
        return (authorize) -> authorize
                .requestMatchers("/").permitAll()
                .requestMatchers("/css/**").permitAll()
                .requestMatchers("/js/**").permitAll()
                .requestMatchers("/error").permitAll()
                .requestMatchers("favicon.ico").permitAll()
                .requestMatchers("/webjars/**").permitAll()
                .anyRequest().authenticated();
    }

    /**
     * @see {@link HttpBasicConfigurer}
     */
    Customizer<HttpBasicConfigurer<HttpSecurity>> httpBasicCustomizer() {
        return (basic) -> basic.disable();
    }

    /**
     * @see {@link FormLoginConfigurer}
     */
    Customizer<FormLoginConfigurer<HttpSecurity>> formLoginCustomizer() {
        return (form) -> form.disable();
    }

    /**
     * @see {@link OAuth2LoginConfigurer}
     */
    Customizer<OAuth2LoginConfigurer<HttpSecurity>> oauth2LoginCustomizer() {
        return (oauth2) -> oauth2.permitAll();
    }

    /**
     * @see {@link LogoutConfigurer}
     */
    Customizer<LogoutConfigurer<HttpSecurity>> logoutCustomizer() {
        return (logout) -> logout.permitAll();
    }

    /**
     * @see {@link CsrfConfigurer}
     */
    Customizer<CsrfConfigurer<HttpSecurity>> csrfCustomizer() {
        return (csrf) -> csrf.disable();
    }

    /**
     * @see {@link CorsConfigurer}
     */
    Customizer<CorsConfigurer<HttpSecurity>> corsCustomizer() {
        return (cors) -> cors.disable();
    }

}
