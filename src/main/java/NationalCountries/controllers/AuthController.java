package NationalCountries.controllers;

import NationalCountries.dto.JwtAuthDto;
import NationalCountries.dto.UserDto;
import NationalCountries.services.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class AuthController {

    private AuthService authService;

    @PostMapping(value = {"login"})
    public ResponseEntity<JwtAuthDto> login(@RequestBody UserDto userDto) {
        String token = authService.login(userDto);
        JwtAuthDto jwtAuthDto = new JwtAuthDto(token);
        return ResponseEntity.ok(jwtAuthDto);
    }

    @PostMapping(value = {"/register"})
    public ResponseEntity<String> register(@RequestBody UserDto registerDto){
        String response = authService.registerUser(registerDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}