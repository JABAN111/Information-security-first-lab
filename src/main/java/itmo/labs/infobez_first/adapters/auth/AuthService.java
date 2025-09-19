package itmo.labs.infobez_first.adapters.auth;

import itmo.labs.infobez_first.adapters.rest.dto.JwtAuthenticationResponse;
import itmo.labs.infobez_first.adapters.rest.dto.AuthDto;
import itmo.labs.infobez_first.core.entity.Role;
import itmo.labs.infobez_first.core.entity.User;
import itmo.labs.infobez_first.core.exceptions.AuthorizeException;
import itmo.labs.infobez_first.core.exceptions.FieldNotSpecifiedException;
import itmo.labs.infobez_first.core.ports.JwtService;
import itmo.labs.infobez_first.core.ports.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@AllArgsConstructor
@Slf4j
public class AuthService {
    private final UserService userService;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    public JwtAuthenticationResponse signUp(AuthDto request) {
        var user = getUserOrThrow(request);
        userService.add(user);
        var jwt = jwtService.generateToken(user);
        return new JwtAuthenticationResponse(jwt);
    }

    public JwtAuthenticationResponse signIn(AuthDto request) {
        if (request.getEmail() == null || request.getEmail().isEmpty()) {
            throw new FieldNotSpecifiedException("Поле email обязательное");
        }
        if (request.getPassword() == null || request.getPassword().isEmpty()) {
            throw new FieldNotSpecifiedException("Поле password обязательное");
        }
        User userEntity;
        try {
            userEntity = userService.getUserByEmail(request.getEmail());
            log.debug("Stored hash: {}", userEntity.getPassword());

            if (!passwordEncoder.matches(request.getPassword(), userEntity.getPassword())) {
                throw new AuthorizeException("Пароль указан неверно");
            }
        } catch (UsernameNotFoundException e) {
            throw new AuthorizeException("Пользователя с заданным email не существует");
        }
        return new JwtAuthenticationResponse(jwtService.generateToken(userEntity));
    }

    private User getUserOrThrow(AuthDto request) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(request.getEmail());
        if (!matcher.matches()) {
            log.error("error, email expect domain, got {}", request.getEmail());
            throw new IllegalArgumentException("Email должен включать в себя домен");
        }

        var user = User.builder()
                .email(request.getEmail())
                .password(request.getPassword())
                .role(Role.ROLE_USER)
                .password(passwordEncoder.encode(request.getPassword()))
                .build();

        if (userService.isExist(user.getUsername())) {
            log.warn("User with username: {} exist", user.getUsername());
            throw new AuthorizeException("Пользователь с именем: " + user.getUsername() +
                    " уже существует");
        }
        return user;
    }

}
