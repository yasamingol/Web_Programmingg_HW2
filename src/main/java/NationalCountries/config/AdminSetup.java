package NationalCountries.config;

import NationalCountries.services.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class AdminSetup {

    private final AdminProperties adminProperties;
    private final AuthService authService;

    @EventListener(ApplicationReadyEvent.class)
    public void setUpAdmin() {
        authService.registerAdmin(adminProperties.getUsername(), adminProperties.getPassword());
    }
}