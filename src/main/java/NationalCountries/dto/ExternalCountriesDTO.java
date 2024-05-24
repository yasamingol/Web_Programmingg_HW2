package NationalCountries.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class ExternalCountriesDTO {
    @JsonProperty("data")
    private List<CountryDTO> countries;

    public List<CountryDTO> getCountries() {
        return countries;
    }

    public void setCountries(List<CountryDTO> countries) {
        this.countries = countries;
    }
}
