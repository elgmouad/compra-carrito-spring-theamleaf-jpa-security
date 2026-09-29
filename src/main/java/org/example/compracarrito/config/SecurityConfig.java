package org.example.compracarrito.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(authorize -> authorize
                        // Autoriser la console H2
                        .requestMatchers("/", "/products", "/cart/**","/h2-console/**").permitAll()
                        // Autoriser les ressources statiques (CSS, JS, images)
                        .requestMatchers("/css/**", "/js/**", "/images/**").permitAll()
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        // Toutes les autres requêtes nécessitent une authentification
                        .anyRequest().authenticated()
                )
                // Désactiver CSRF pour la console H2 (sinon les formulaires sont rejetés)
                .csrf(csrf -> csrf
                        .ignoringRequestMatchers("/h2-console/**")
                )
                // Autoriser les iframes de même origine (l'interface H2 en utilise)
                .headers(headers -> headers
                        .frameOptions(frame -> frame.sameOrigin())
                )
                // Configuration du formulaire de login personnalisé
                .formLogin(form -> form
                        .loginPage("/login")
                        .permitAll()
                )
                // Configuration du logout
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout")
                        .permitAll()
                )
                .build();
    }
}