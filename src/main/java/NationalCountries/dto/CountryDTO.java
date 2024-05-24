package NationalCountries.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CountryDTO {
    @JsonProperty("country")
    private String name;

    public CountryDTO() {}

    public CountryDTO(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
