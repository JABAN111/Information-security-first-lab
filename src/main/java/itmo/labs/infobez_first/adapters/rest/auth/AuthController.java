package itmo.labs.infobez_first.adapters.rest.auth;


import itmo.labs.infobez_first.adapters.auth.AuthService;
import itmo.labs.infobez_first.adapters.rest.util.dto.AuthDto;
import itmo.labs.infobez_first.adapters.rest.util.dto.JwtAuthenticationResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.text.StringEscapeUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@Slf4j
@AllArgsConstructor
public class AuthController {

    private AuthService authService;

    @PostMapping("/registration")
    public JwtAuthenticationResponse signUp(@RequestBody AuthDto request) {
        request.setEmail(StringEscapeUtils.escapeHtml4(request.getEmail()));
        request.setPassword(StringEscapeUtils.escapeHtml4(request.getPassword()));
        return authService.signUp(request);
    }

    @PostMapping("/login")
    public JwtAuthenticationResponse signIn(@RequestBody AuthDto authDto) {
        authDto.setEmail(StringEscapeUtils.escapeHtml4(authDto.getEmail()));
        authDto.setPassword(StringEscapeUtils.escapeHtml4(authDto.getPassword()));
        return authService.signIn(authDto);
    }
}
