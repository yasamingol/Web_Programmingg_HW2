package NationalCountries.services;

import NationalCountries.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    @Autowired
    private UserRepository userRepository;

    @Transactional
    public boolean updateUserActivation(String username, boolean active) {
        return userRepository.findByUsername(username)
                .map(user -> {
                    user.setEnabled(active);
                    userRepository.save(user);
                    return true;
                })
                .orElse(false);
    }
}
