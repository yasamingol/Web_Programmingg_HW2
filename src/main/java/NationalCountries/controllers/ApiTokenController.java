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

    @PostMapping
    public ApiTokenResponse createToken(Authentication authentication, @RequestBody CreateApiTokenRequest request) {
        ApiToken apiToken = apiTokenService.createToken(request.getName(), request.getExpirationDate(), authentication.getName());
        return new ApiTokenResponse(apiToken.getName(), apiToken.getExpirationDate(), "API " + apiToken.getToken());
    }

    @GetMapping
    public List<ApiTokenResponse> getTokens(Authentication authentication) {
        List<ApiToken> tokens = apiTokenService.findAllByUser(authentication.getName());
        return tokens.stream()
                .map(token -> new ApiTokenResponse(token.getName(), token.getExpirationDate(), "API " + token.getToken()))
                .collect(Collectors.toList());
    }

    @DeleteMapping
    public void deleteToken(Authentication authentication, @RequestHeader("Authorization") String token) {
        String tokenValue = token.substring(4);
        apiTokenService.deleteToken(tokenValue, authentication.getName());
    }
}
