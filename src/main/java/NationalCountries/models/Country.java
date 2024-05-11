package NationalCountries.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Country {
    @JsonProperty("country")
    private String name;

    public Country() {}

    public Country(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
