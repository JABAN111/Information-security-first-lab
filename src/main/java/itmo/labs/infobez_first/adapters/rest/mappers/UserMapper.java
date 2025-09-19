package itmo.labs.infobez_first.adapters.rest.mappers;

import itmo.labs.infobez_first.adapters.rest.dto.UserDto;
import itmo.labs.infobez_first.core.entity.User;

public class UserMapper {
    public User toEntity(UserDto userDto) {
        User user = new User();
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        return user;
    }

    public UserDto toDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());
        userDto.setRole(user.getRole());
        return userDto;
    }
}
