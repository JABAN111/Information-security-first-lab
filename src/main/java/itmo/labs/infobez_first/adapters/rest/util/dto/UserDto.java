package itmo.labs.infobez_first.adapters.rest.util.dto;

import itmo.labs.infobez_first.core.entity.Role;
import lombok.Data;

@Data
public class UserDto {
    private String email;

    private String password;

    private Role role;
}
