package lt.viko.eif.service;

import lt.viko.eif.entity.UserDto;

public interface  UserService {
    UserDto getByUsername(String username);

    UserDto create(UserDto user);

    UserDto updatePassword(String username, String password);

    void deleteByUsername(String username);

    boolean login(UserDto user);
}
