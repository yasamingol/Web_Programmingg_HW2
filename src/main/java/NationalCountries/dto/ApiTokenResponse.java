package NationalCountries.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class ApiTokenResponse {
    private String token;
    private String name;
    private Date expirationDate;
}