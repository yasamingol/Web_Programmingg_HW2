package NationalCountries.entity;

import NationalCountries.entity.enums.UserRole;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique = true)
    private String username;
    @Column(nullable=false)
    private String password;
    @Column(nullable=false)
    private boolean isEnabled;
    @Column(nullable = false)
    private UserRole role;

    public User(String username, String password, boolean isEnabled, UserRole role) {
        super();
        this.username = username;
        this.password = password;
        this.isEnabled = isEnabled;
        this.role = role;
    }

}