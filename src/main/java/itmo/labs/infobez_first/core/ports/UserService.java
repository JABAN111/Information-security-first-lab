package itmo.labs.infobez_first.core.ports;

import itmo.labs.infobez_first.core.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService {
    User add(User user);

    User getUserByEmail(String username);

    User updateUser(User user);

    boolean isExist(String username);

    UserDetailsService getUserDetailsService();
}
