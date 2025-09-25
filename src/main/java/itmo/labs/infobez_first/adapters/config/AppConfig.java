package itmo.labs.infobez_first.adapters.config;

import itmo.labs.infobez_first.adapters.auth.AuthService;
import itmo.labs.infobez_first.adapters.rest.music.MusicController;
import itmo.labs.infobez_first.adapters.security.JwtAuthenticationFilter;
import itmo.labs.infobez_first.core.ports.JwtService;
import itmo.labs.infobez_first.core.ports.MusicService;
import itmo.labs.infobez_first.core.ports.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Supplier;

@Configuration
public class AppConfig {

    @Bean
    public Supplier<UserService> userServiceSupplier(UserService userService) {
        return () -> userService;
    }
    @Bean
    public Supplier<MusicService> musicServiceSupplier(MusicService service) {
        return () -> service;
    }

    @Bean
    public Supplier<AuthService> authServiceSupplier(AuthService service) {
        return () -> service;
    }

    @Bean
    public Supplier<JwtService> authServiceSupplier(JwtService service) {
        return () -> service;
    }

    @Bean
    public Supplier<JwtAuthenticationFilter> authServiceSupplier(JwtAuthenticationFilter service) {
        return () -> service;
    }
}
