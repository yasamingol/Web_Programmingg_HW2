package NationalCountries.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class ExternalCountries {
    @JsonProperty("data")
    private List<Country> countries;

    public List<Country> getCountries() {
        return countries;
    }

    public void setCountries(List<Country> countries) {
        this.countries = countries;
    }
}
