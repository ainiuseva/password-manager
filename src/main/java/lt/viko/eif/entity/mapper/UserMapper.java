package lt.viko.eif.entity.mapper;

import lt.viko.eif.entity.User;
import lt.viko.eif.entity.UserDto;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User dtoToObject(UserDto dto) {
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());

        return user;
    }

    public UserDto objectToDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setUsername(user.getUsername());
        userDto.setPassword(user.getPassword());

        return userDto;
    }
}
