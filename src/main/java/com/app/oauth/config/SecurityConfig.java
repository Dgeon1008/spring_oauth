package com.app.oauth.config;

import com.app.oauth.util.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.HashMap;
import java.util.Map;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class SecurityConfig {

    private final JwtTokenUtil jwtTokenUtil;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(cors -> cors.configurationSource(corsConfigurationSource())) // CORS 설정 추가
                .csrf(csrf -> csrf.disable()) // CSRF 비활성화
                .authorizeHttpRequests(authorize -> authorize
                        .anyRequest().permitAll() // 모든 경로 허용
                )
                .oauth2Login(oauth -> oauth
                        .loginPage("/oauth2/authorization/google")
                        .successHandler((request, response, authentication) -> {
//                            log.info("{}", authentication);
                            if(authentication instanceof OAuth2AuthenticationToken) {
                                OAuth2AuthenticationToken authToken = (OAuth2AuthenticationToken) authentication;
                                OAuth2User user = authToken.getPrincipal();
                                Map<String, Object> attributes = user.getAttributes();
                                                                
//                                log.info("oauth2AuthenticationToken : {}", authToken);
                                
//                                Oauth2 제공자의 정보(email, name, provider) 어디서 회원가입을 했는지 정보를 가져옴
                                String provider = authToken.getAuthorizedClientRegistrationId(); // ex) "google", "naver", "kakao"
                                String email = (String)attributes.get("email");
                                String name = (String)attributes.get("name");

                                Map<String, Object> responcseMap = new HashMap<>();
                                Map<String, Object> claims = new HashMap<>();

//                                회원을 확인한다.
//                                1) 이미 회원인지
//                                2) 최초 구글, 카카오, 네이버, 소션 로그인을 한 사람인지
//                                  - 1) 바로 회원가입 후 로그인
//                                  - 2) 리다이렉트 -> 성공 페이지 -> 회원 가입 -> 백엔드로 회원가입 요청
                            }
                        })
                );

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOrigin("http://localhost:3000"); // React 앱 주소
        configuration.addAllowedMethod("*"); // 모든 HTTP 메서드 허용
        configuration.addAllowedHeader("*"); // 모든 요청 헤더 허용
        configuration.setAllowCredentials(true); // 인증 정보 허용

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration); // 모든 경로에 적용
        return source;
    }
}
