package NationalCountries.controllers;

import NationalCountries.dto.CreateApiTokenRequest;
import NationalCountries.dto.ApiTokenResponse;
import NationalCountries.entity.ApiToken;
import NationalCountries.entity.User;
import NationalCountries.repository.UserRepository;
import NationalCountries.services.ApiTokenService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user/api-tokens")
@AllArgsConstructor
public class ApiTokenController {

    private ApiTokenService apiTokenService;
    private UserRepository userRepository;

    @PostMapping
    public ApiTokenResponse createToken(Authentication authentication, @RequestBody CreateApiTokenRequest request) {
        User user = userRepository.findByUsername(authentication.getName())
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found with username: "+ authentication.getName()));
        System.out.println("Sag!");
        ApiToken apiToken = apiTokenService.createToken(request.getName(), request.getExpirationDate(), user);
        return new ApiTokenResponse(apiToken.getToken(), apiToken.getName(), apiToken.getExpirationDate());
    }

    @GetMapping
    public List<ApiTokenResponse> getTokens(Authentication authentication) {
        User user = userRepository.findByUsername(authentication.getName())
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found with username: "+ authentication.getName()));
        List<ApiToken> tokens = apiTokenService.findAllByUser(user);
        return tokens.stream()
                .map(token -> new ApiTokenResponse(token.getToken(), token.getName(), token.getExpirationDate()))
                .collect(Collectors.toList());
    }

    @DeleteMapping
    public void deleteToken(Authentication authentication, @RequestHeader("Authorization") String token) {
        String tokenValue = token.substring(7); // Remove "Bearer " prefix
        apiTokenService.deleteToken(tokenValue, authentication.getName());
    }
}
