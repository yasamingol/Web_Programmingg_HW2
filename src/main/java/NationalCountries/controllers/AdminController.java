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

    @PutMapping(value = {"/users/{username}/{active}"})
//    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<String> updateUserActivation(@PathVariable String username, @PathVariable boolean active) {
        System.out.println("Came Here! - Username: " + username + " Active: " + active);

        boolean isUpdated = adminService.updateUserActivation(username, active);
        if (isUpdated) {
            return ResponseEntity.ok("User activation updated successfully.");
        } else {
            return ResponseEntity.badRequest().body("Failed to update user activation.");
        }
    }
}
