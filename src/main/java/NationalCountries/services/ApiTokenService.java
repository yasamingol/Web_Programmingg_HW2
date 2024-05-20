package NationalCountries.services;

import NationalCountries.entity.ApiToken;
import NationalCountries.entity.User;
import NationalCountries.repository.ApiTokenRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ApiTokenService {

    private ApiTokenRepository apiTokenRepository;

    public ApiToken createToken(String name, Date expirationDate, User user) {
        String tokenValue = generateTokenValue();
        ApiToken apiToken = new ApiToken(tokenValue, name, expirationDate, user);
        return apiTokenRepository.save(apiToken);
    }

    public List<ApiToken> findAllByUser(User user) {
        return apiTokenRepository.findAllByUser(user);
    }

    public void deleteToken(String token, String username) {
        Optional<ApiToken> apiToken = apiTokenRepository.findApiTokenByToken(token);
        if (apiToken.isPresent() && apiToken.get().getUser().getUsername().equals(username)) {
            apiTokenRepository.delete(apiToken.get());
        } else {
            throw new RuntimeException("Token not found or unauthorized");
        }
    }

    private String generateTokenValue() {
        // Implement token generation logic here (e.g., UUID.randomUUID().toString())
        return "OK!";
    }
}
