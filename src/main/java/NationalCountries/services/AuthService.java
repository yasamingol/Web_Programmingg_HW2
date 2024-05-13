package NationalCountries.services;

import NationalCountries.entity.User;
import NationalCountries.entity.enums.UserRole;
import NationalCountries.models.UserDto;
import NationalCountries.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;

@AllArgsConstructor
public class AuthService {

    private PasswordEncoder passwordEncoder;

    private AuthenticationManager authenticationManager;

    private UserRepository userRepository;

//    private JwtToken

    public String registerUser(UserDto userDto) {
        if (userRepository.existsByUsername(userDto.getUsername())) {
            return "Fail"; //TODO Complete Exception Handling
        }

        User user = new User(userDto.getUsername(),
                passwordEncoder.encode(userDto.getPassword()),
                false, UserRole.USER);

        userRepository.save(user);

        return "Success"; //TODO Complete Exception Handling

    }

    public String registerAdmin(UserDto userDto) {
        User user = new User(userDto.getUsername(),
                passwordEncoder.encode(userDto.getPassword()),
                true, UserRole.ADMIN);

        userRepository.save(user);

        return "Success";
    }

    public String login(UserDto userDto) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                userDto.getUsername(), userDto.getPassword()
        ));

        SecurityContextHolder.getContext().setAuthentication(authentication);

//        String jwtToken = jwtTokenProvider
        return "Success";
    }
}
