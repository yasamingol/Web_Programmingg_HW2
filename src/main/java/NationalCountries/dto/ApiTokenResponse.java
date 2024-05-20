package NationalCountries.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class ApiTokenResponse {
    private String name;
    private Date expirationDate;
    private String token;
}