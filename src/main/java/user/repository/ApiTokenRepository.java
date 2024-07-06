package user.repository;

import user.entity.ApiToken;
import user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ApiTokenRepository extends JpaRepository<ApiToken, Long> {
    Optional<ApiToken> findApiTokenByToken(String token);

    List<ApiToken> findAllByUser(User user);
}
