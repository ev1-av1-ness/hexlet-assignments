package exercise;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.http.HttpMethod.POST;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

    final UserDetailsServiceImpl userDetailsService;

    @Bean
    // Переопределяет схему аутентификации
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // BEGIN
        http.authorizeRequests()
                .requestMatchers(POST, "/users").permitAll()
                .requestMatchers("/").permitAll()
                .anyRequest().authenticated()
                .and().csrf().disable()
                .httpBasic();

        return http.build();
        // END
    }

    // Указываем, что для сравнения хешей паролей
    // будет использоваться кодировщик BCrypt
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}



