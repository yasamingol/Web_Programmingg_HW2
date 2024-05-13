package NationalCountries.repository;
import NationalCountries.entity.User;
import NationalCountries.models.UserDto;
import jakarta.annotation.Nonnull;
import org.springframework.data.jpa.repository.JpaRepository;
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

    Boolean existsByUsername(String username);

}
