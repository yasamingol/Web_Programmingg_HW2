package NationalCountries.repository;

import NationalCountries.entity.ApiToken;
import NationalCountries.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface ApiTokenRepository extends JpaRepository<ApiToken, Long> {
    Optional<ApiToken> findApiTokenByToken(String token);

    List<ApiToken> findAllByUser(User user);
}
