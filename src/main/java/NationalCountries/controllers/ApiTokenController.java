package NationalCountries.controllers;

import NationalCountries.dto.CreateApiTokenRequest;
import NationalCountries.dto.ApiTokenResponse;
import NationalCountries.entity.ApiToken;
import NationalCountries.services.ApiTokenService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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
    public Map<String, Object> getTokens(Authentication authentication, @RequestParam(defaultValue = "1") int pageNumber,
                                         @RequestParam(defaultValue = "100") int pageSize) {
        return apiTokenService.findAllByUser(authentication.getName(), pageNumber, pageSize);
    }

    @DeleteMapping
    public void deleteToken(Authentication authentication, @RequestHeader("Authorization") String token) {
        String tokenValue = token.substring(4);
        apiTokenService.deleteToken(tokenValue, authentication.getName());
    }
}
