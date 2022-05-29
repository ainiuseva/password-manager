package lt.viko.eif.service.impl;

import lt.viko.eif.entity.User;
import lt.viko.eif.entity.UserDto;
import lt.viko.eif.entity.mapper.UserMapper;
import lt.viko.eif.random.PasswordRandomizer;
import lt.viko.eif.repository.UserRepository;
import lt.viko.eif.security.AesAlgorithm;
import lt.viko.eif.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final UserMapper mapper;
    private final AesAlgorithm aes;
    private final PasswordRandomizer randomizer;

    @Autowired
    public UserServiceImpl(UserRepository repository, UserMapper mapper, AesAlgorithm aes, PasswordRandomizer randomizer) {
        this.repository = repository;
        this.mapper = mapper;
        this.aes = aes;
        this.randomizer = randomizer;
    }

    @Override
    public UserDto getByUsername(String username) {
        boolean isExist = repository.existsUserByUsername(username);
        if (isExist) {
            User user = repository.getByUsername(username);
            return mapper.objectToDto(user);
        } else {
            return null;
        }
    }

    @Override
    public UserDto create(UserDto user) {
        if (user.getPassword() == null || user.getPassword().length() == 0) {
            String randomPassword = randomizer.getRandomPassword();
            user.setPassword(randomPassword);
        }

        String encryptedPassword = aes.encrypt(user.getPassword());
        User userToCreate = mapper.dtoToObject(user);
        userToCreate.setPassword(encryptedPassword);

        User savedUser = repository.save(userToCreate);

        return mapper.objectToDto(savedUser);
    }

    @Transactional
    @Override
    public UserDto updatePassword(String username, String password) {
        boolean isExist = repository.existsUserByUsername(username);

        if (!isExist) {
            return null;
        }

        User user = repository.getByUsername(username);

        String newEncryptedPassword = aes.encrypt(password);
        user.setPassword(newEncryptedPassword);

        return mapper.objectToDto(user);
    }

    @Transactional
    @Override
    public void deleteByUsername(String username) {
        repository.deleteByUsername(username);
    }

    @Override
    public boolean login(UserDto user) {
        boolean isExist = repository.existsUserByUsername(user.getUsername());

        if (!isExist) {
            return false;
        }

        String encryptedEnteredPassword = aes.encrypt(user.getPassword());
        User trueUser = repository.getByUsername(user.getUsername());

        return encryptedEnteredPassword.equals(trueUser.getPassword());
    }
}
