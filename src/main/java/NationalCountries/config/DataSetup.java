package NationalCountries.config;

import NationalCountries.entity.Role;
import NationalCountries.repository.RoleRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class DataSetup {

    @Autowired
    private RoleRepository roleRepository;

    @PostConstruct
    @Transactional
    public void initRoles() {
        String[] roles = {"ROLE_ADMIN", "ROLE_USER"};
        for (String role : roles) {
            roleRepository.findByName(role).ifPresentOrElse(
                    existingRole -> {},
                    () -> {
                        Role newRole = new Role();
                        newRole.setName(role);
                        roleRepository.save(newRole);
                    }
            );
        }
    }
}


