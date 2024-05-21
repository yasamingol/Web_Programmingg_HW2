package NationalCountries.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CountryDetailDTO {

    @JsonProperty("name")
    private String name;
    @JsonProperty("capital")
    private String capital;
    @JsonProperty("iso2")
    private String iso2;
    @JsonProperty("population")
    private double population;
    @JsonProperty("population_growth")
    private double pop_growth;
    @JsonProperty("currency")
    private Currency currency;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Currency {
        @JsonProperty("code")
        private String code;
        @JsonProperty("name")
        private String name;

    }
}
