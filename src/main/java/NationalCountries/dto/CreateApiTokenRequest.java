package NationalCountries.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class CreateApiTokenRequest {
    private String name;

    @JsonProperty("expire_date")
    private Date expirationDate;
}