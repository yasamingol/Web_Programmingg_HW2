// NationalCountries/models/ExternalCountry.java
package NationalCountries.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ExternalCountry {
    @JsonProperty("country")
    private String name;

    // Getter and Setter
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
