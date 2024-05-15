package NationalCountries.controllers;

import NationalCountries.dto.JwtAuthDto;
import NationalCountries.dto.UserDto;
import NationalCountries.services.AuthService;
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
    public ResponseEntity<JwtAuthDto> login(@RequestBody UserDto userDto) {
        String token = "";
        try {
            token = authService.login(userDto);
        } catch (Exception e) {
            System.out.println("Error while processing login: " + e.getMessage());
        } // TODO: Cleaner Exception Handling!
        JwtAuthDto jwtAuthDto = new JwtAuthDto(token);
        return ResponseEntity.ok(jwtAuthDto); // TODO: Should it return anything?
    }

    @PostMapping(value = {"/register"})
    public ResponseEntity<String> register(@RequestBody UserDto userDto){
        String response = authService.registerUser(userDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
