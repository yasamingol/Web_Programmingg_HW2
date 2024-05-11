// NationalCountries/models/ExternalCountriesResponse.java
package NationalCountries.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class ExternalCountriesResponse {
    @JsonProperty("data")
    private List<ExternalCountry> countries;

    // Getter and Setter
    public List<ExternalCountry> getCountries() {
        return countries;
    }

    public void setCountries(List<ExternalCountry> countries) {
        this.countries = countries;
    }
}
