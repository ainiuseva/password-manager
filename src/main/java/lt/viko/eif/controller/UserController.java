package lt.viko.eif.controller;

import lt.viko.eif.entity.UserDto;
import lt.viko.eif.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping(params = "username")
    public ResponseEntity<UserDto> getByUsername(@RequestParam String username) {
        UserDto user = service.getByUsername(username);

        return ResponseEntity.ok(user);
    }

    @PostMapping
    public ResponseEntity<UserDto> create(@RequestBody UserDto user) {
        UserDto createdUser = service.create(user);

        return ResponseEntity.ok(createdUser);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserDto user) {
        boolean isSuccessful = service.login(user);

        String message;
        if (isSuccessful) {
            message = "Successfully logged in";
        } else {
            message = "Wrong credentials";
        }

        return ResponseEntity.ok(message);
    }

    @PutMapping
    public ResponseEntity<UserDto> update(@RequestBody UserDto user) {
        UserDto createdUser = service.updatePassword(user.getUsername(), user.getPassword());

        return ResponseEntity.ok(createdUser);
    }

    @DeleteMapping(params = "username")
    public ResponseEntity<Void> delete(@RequestParam String username) {
        service.deleteByUsername(username);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
