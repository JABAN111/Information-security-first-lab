package itmo.labs.infobez_first.adapters.rest.util.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthDto {
    private String email;
    private String password;
}
