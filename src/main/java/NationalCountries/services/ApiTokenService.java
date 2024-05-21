package NationalCountries.services;

import NationalCountries.dto.ApiTokenResponse;
import NationalCountries.entity.ApiToken;
import NationalCountries.entity.User;
import NationalCountries.repository.ApiTokenRepository;
import NationalCountries.repository.UserRepository;
import NationalCountries.services.Security.JwtTokenProviderService;
import NationalCountries.services.UIRepresentationsServices.PaginationService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ApiTokenService {

    private ApiTokenRepository apiTokenRepository;
    private JwtTokenProviderService jwtTokenProviderService;
    private UserRepository userRepository;
    private RedisTemplate<String, String> redisTemplate;
    private PaginationService paginationService;

    public ApiToken createToken(String name, Date expirationDate, String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found with username: " + username));
        String tokenValue = jwtTokenProviderService.generateToken(username, expirationDate);
        ApiToken apiToken = new ApiToken(tokenValue, name, expirationDate, user);
        return apiTokenRepository.save(apiToken);
    }

    public Map<String, Object> findAllByUser(String username, int pageNumber, int pageSize) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found with username: " + username));
        Map<String, Object> response = new HashMap<>();
        List<ApiToken> allTokens = apiTokenRepository.findAllByUser(user);
        response.put("tokens", paginationService.paginate(allTokens.stream()
                .map(token -> new ApiTokenResponse(token.getName(), token.getExpirationDate(), "API " + token.getToken()))
                .collect(Collectors.toList()), pageNumber, pageSize));
        response.put("count", allTokens.size());
        return response;
    }

    public void deleteToken(String token, String username) {
        Optional<ApiToken> apiToken = apiTokenRepository.findApiTokenByToken(token);
        if (apiToken.isPresent() && apiToken.get().getUser().getUsername().equals(username)) {
            apiTokenRepository.delete(apiToken.get());
            blacklistToken(token, apiToken.get().getExpirationDate());
        } else {
            throw new RuntimeException("Token not found or unauthorized"); //TODO Exception handling
        }
    }

    private void blacklistToken(String token, Date expirationDate) {
        ValueOperations<String, String> ops = redisTemplate.opsForValue();
        ops.set(token, "blacklisted", expirationDate.getTime() - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
    }

}