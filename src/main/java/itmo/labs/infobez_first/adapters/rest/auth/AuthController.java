package itmo.labs.infobez_first.adapters.rest.auth;


import itmo.labs.infobez_first.adapters.auth.AuthService;
import itmo.labs.infobez_first.adapters.rest.dto.AuthDto;
import itmo.labs.infobez_first.adapters.rest.dto.JwtAuthenticationResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@Slf4j
@AllArgsConstructor
public class AuthController {

    private AuthService authService;

    @PostMapping("/registration")
    public JwtAuthenticationResponse signUp(AuthDto authDto) {
        return authService.signUp(authDto);
    }

    @PostMapping("/login")
    public JwtAuthenticationResponse signIn(AuthDto authDto) {
        return authService.signIn(authDto);
    }
}
