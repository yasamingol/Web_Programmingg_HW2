package NationalCountries.controllers;

import NationalCountries.services.AdminService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
@AllArgsConstructor
public class AdminController {

    private AdminService adminService;

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
