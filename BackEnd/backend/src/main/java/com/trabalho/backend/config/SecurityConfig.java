package com.trabalho.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            // Desabilita CSRF e CORS
            .csrf(csrf -> csrf.disable())
            .cors(cors -> cors.disable())
            
            // Configura as permissões de acesso
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/admin/registrar", "/admin/logar", "/admin/listar").permitAll()
                .anyRequest().permitAll() // <— isso garante que tudo está liberado
            )

            // Desativa login padrão e basic auth
            .formLogin(form -> form.disable())
            .httpBasic(Customizer.withDefaults());

        return http.build();
    }
}

