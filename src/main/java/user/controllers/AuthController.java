package user.controllers;

import user.dto.JwtAuthDto;
import user.dto.UserDto;
import user.exceptions.LoginException;
import user.exceptions.RegistrationException;
import user.services.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class AuthController {

    private AuthService authService;

    @GetMapping(value = {"/meow"})
    public ResponseEntity<String> meower() {
        return ResponseEntity.ok("Meow");
    }

    @PostMapping(value = {"/login"})
    public ResponseEntity<JwtAuthDto> login(@RequestBody UserDto userDto) throws LoginException {
        String token = "";
        try {
            token = authService.login(userDto);
        } catch (Exception e) {
            throw new LoginException("Error while processing login: " + e.getMessage(), e);
        }
        JwtAuthDto jwtAuthDto = new JwtAuthDto(token);
        return ResponseEntity.ok(jwtAuthDto);
    }

    @PostMapping(value = {"/register"})
    public ResponseEntity<String> register(@RequestBody UserDto userDto) throws RegistrationException {
        try {
            authService.registerUser(userDto);
            return new ResponseEntity<>("Registration successful", HttpStatus.CREATED);
        } catch (RegistrationException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
