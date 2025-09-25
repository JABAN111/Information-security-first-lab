package itmo.labs.infobez_first.adapters.auth;


import itmo.labs.infobez_first.core.ports.UserRepository;
import itmo.labs.infobez_first.core.entity.User;
import itmo.labs.infobez_first.core.ports.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User add(final User user) {
        user.setPassword(user.getPassword());
        return userRepository.save(user);
    }

    @Override
    public User updateUser(final User user) {
        User newUser = userRepository.save(user);
        log.info("{} updated successfully", user.getUsername());
        return newUser;
    }

    @Override
    public boolean isExist(final String email) {
        Optional<User> potentialUser = userRepository.findByEmail(email);
        if (potentialUser.isPresent()) {
            log.info("User with username: {} exist", email);
            return true;
        }
        log.info("User with username: {} not exist", email);
        return false;
    }

    @Override
    public User getUserByEmail(final String email) {
        return userRepository.findByEmail(email.trim()).orElseThrow(() -> new UsernameNotFoundException("User with username: " + email + " not found"));
    }

    @Override
    public UserDetailsService getUserDetailsService() {
        return this::getUserByEmail;
    }
}
