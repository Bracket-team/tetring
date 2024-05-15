package bracket.tetring.global.login;


import bracket.tetring.global.login.domain.Role;
import bracket.tetring.global.login.service.CustomOAuth2UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private final CustomOAuth2UserService customOAuth2UserService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http
                .csrf(AbstractHttpConfigurer::disable)  //csrf 비활성화 -> cookie를 사용하지 않으면 꺼도 된다. (cookie를 사용할 경우 httpOnly(XSS 방어), sameSite(CSRF 방어)로 방어해야 한다.)
                .headers(
                        (headerConfig) -> headerConfig.frameOptions(
                                HeadersConfigurer.FrameOptionsConfig::disable
                        )
                )
                .httpBasic(AbstractHttpConfigurer::disable) // 기본 인증 로그인 비활성화
                .authorizeHttpRequests((authorizeRequest) -> authorizeRequest // 특정 경로 또는 패턴에 대한 접근 제어
                        .requestMatchers("/", "/index.html", "/css/**", "/images/**", "/js/**", "/h2-console/**").permitAll()
                        .requestMatchers("/api/v1/**").hasRole(Role.USER.name()) // /api/v1/의 하위경로에 대한 접근을 USER 권한을 가진 사용자에게 허용
                        .anyRequest().authenticated() // 설정된 경로 이외의 모든 요청에 대해 인증된(로그인) 사용자에게만 접근을 허용
                )

                .logout( // 로그아웃 성공 시 / 주소로 이동
                        (logoutConfig) -> logoutConfig.logoutSuccessUrl("/")
                )
                .formLogin((formlogin)->formlogin
                        .loginPage("/login.html") // 로그인 페이지로 사용할 html
                        .loginProcessingUrl("/login") // 로그인 처리 URL 지정
                        .defaultSuccessUrl("/") // 로그인 성공시 리다이렉션할 URL
                        .failureUrl("/login.html?error=true") // 로그인 실패시 리다이렉션할 URL
                        .permitAll()) // 단순 허용

                // OAuth2 로그인 기능에 대한 여러 설정
                .oauth2Login(Customizer.withDefaults()); // 아래 코드와 동일한 결과
        /*
                // oauth2 설정
                .oauth2Login(oauth -> // OAuth2 로그인 기능에 대한 여러 설정의 진입점
                        // OAuth2 로그인 성공 이후 사용자 정보를 가져올 때의 설정을 담당
                        oauth.userInfoEndpoint(c -> c.userService(oAuth2UserService))
                                // 로그인 성공 시 핸들러
                                .successHandler(oAuth2SuccessHandler)
                )
        */
                return http.build();
    }
}
