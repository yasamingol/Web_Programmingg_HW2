package NationalCountries.services;

import NationalCountries.entity.Role;
import NationalCountries.entity.User;
import NationalCountries.dto.UserDto;
import NationalCountries.exceptions.RegistrationException;
import NationalCountries.repository.RoleRepository;
import NationalCountries.repository.UserRepository;
import NationalCountries.services.Security.JwtTokenProviderService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@AllArgsConstructor
@Service
public class AuthService {

    private PasswordEncoder passwordEncoder;
    private AuthenticationManager authenticationManager;
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private JwtTokenProviderService jwtTokenProviderService;

    public ResponseEntity<String> registerUser(UserDto userDto) {
        if (userRepository.existsByUsername(userDto.getUsername())) {
            throw new RegistrationException("Error while processing registration: Username is already taken.");
        }

        User user = new User(userDto.getUsername(),
                passwordEncoder.encode(userDto.getPassword()));

        userRepository.save(user);

        return ResponseEntity.ok("Success");
    }

    @Transactional
    public User registerAdmin(String username, String password) {
        return userRepository.findByUsername(username)
                .orElseGet(() -> {
                    Role adminRole = roleRepository.findByName("ROLE_ADMIN")
                            .orElseThrow(() -> new RuntimeException("Admin role not found"));
                    User user = new User();
                    user.setUsername(username);
                    user.setPassword(passwordEncoder.encode(password));
                    user.setEnabled(true);
                    user.setRoles(Collections.singleton(adminRole));
                    userRepository.save(user);
                    return user;
                });
    }


    public String login(UserDto userDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userDto.getUsername(), userDto.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwtToken = jwtTokenProviderService.generateToken(authentication);

        return jwtToken;
    }
}
