package mint.dev.infrastructure.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    //Segurança de acesso as rotas
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Desabilita o CSRF
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/api/v1/users").permitAll()
                        .requestMatchers("/api/v1/tasks").permitAll()
                        .requestMatchers("/api/v1/users/{id}").permitAll()
                        .requestMatchers("/api/v1/wallets").permitAll()
                        .requestMatchers("/api/v1/wallets/*").permitAll()
                        .requestMatchers("/api/v1/wallets/remove/**").permitAll()
                        .requestMatchers("/api/v1/wallets/add/**").permitAll()
                        .requestMatchers("/api/v1/wallets/{id}").permitAll()
                        .requestMatchers("api/v1/auth/**").permitAll()
                        .anyRequest().authenticated() // Requer autenticação para outros endpoints
                );
        return http.build();

    }

    // Segurança de encrypt para senhas de usuários
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
