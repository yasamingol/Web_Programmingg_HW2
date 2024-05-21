package NationalCountries.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WeatherInfoDTO {

    @JsonProperty("country_name")
    private String countryName;

    @JsonProperty("capital")
    private String capital;

    @JsonProperty("wind_speed")
    private double windSpeed;

    @JsonProperty("wind_degrees")
    private int windDegrees;

    @JsonProperty("temp")
    private int temp;

    @JsonProperty("humidity")
    private int humidity;

}
