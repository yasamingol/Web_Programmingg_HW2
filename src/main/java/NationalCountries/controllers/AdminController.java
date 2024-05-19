package NationalCountries.controllers;

import NationalCountries.dto.UserDto;
import NationalCountries.services.AdminService;
import NationalCountries.services.AppUserDetailsService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin")
@AllArgsConstructor
public class AdminController {

    private AdminService adminService;
    private AppUserDetailsService userDetailsService;

    @GetMapping(value = "/users")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public ResponseEntity<Map<String, Object>> listAllUsers() {
        List<UserDto> users = userDetailsService.getAllUsers();
        Map<String, Object> response = new HashMap<>();
        response.put("users", users);
        return ResponseEntity.ok(response);
    }

    @PutMapping(value = "/users/")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<String> updateUserActivation(@RequestParam String username, @RequestParam boolean active) {
        System.out.println("Came Here! - Username: " + username + " Active: " + active);

        boolean isUpdated = adminService.updateUserActivation(username, active);
        if (isUpdated) {
            return ResponseEntity.ok("User activation updated successfully.");
        } else {
            return ResponseEntity.badRequest().body("Failed to update user activation.");
        }
    }
}