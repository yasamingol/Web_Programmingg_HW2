package NationalCountries.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {

    private String username;
    private String password;

    public UserDto(String username, String password) {
        super();
        this.username = username;
        this.password = password;
    }
}
