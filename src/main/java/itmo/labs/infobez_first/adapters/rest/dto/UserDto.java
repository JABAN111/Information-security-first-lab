package itmo.labs.infobez_first.adapters.rest.dto;

import itmo.labs.infobez_first.core.entity.Role;
import lombok.Data;

@Data
public class UserDto {
    private String email;

    private String password;

    private Role role;
}
