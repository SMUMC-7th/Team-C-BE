package umc.teamc.youthStepUp.global.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import umc.teamc.youthStepUp.auth.jwt.JwtProvider;
import umc.teamc.youthStepUp.auth.jwt.filter.JwtExceptionFilter;
import umc.teamc.youthStepUp.auth.jwt.filter.JwtFilter;
import umc.teamc.youthStepUp.auth.service.AuthService;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final ObjectMapper objectMapper;
    private final AuthService authService;
    private final JwtProvider jwtProvider;

    @Bean
    public JwtExceptionFilter jwtExceptionFilter() {
        return new JwtExceptionFilter(objectMapper, authService);
    }

    @Bean
    public JwtFilter jwtFilter() {
        return new JwtFilter(jwtProvider, authService);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(cors -> cors.configurationSource(new CorsConfig().corsConfigurationSource()))
                .csrf(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/", "/swagger-ui/index.html", "/auth/kakao-login", "/auth/kakao-oauth")
                        .permitAll()
                        .anyRequest().permitAll()
                )
                .addFilterBefore(jwtFilter(), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(jwtExceptionFilter(), JwtFilter.class);
        return http.build();

    }

}
