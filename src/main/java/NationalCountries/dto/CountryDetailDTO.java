package NationalCountries.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CountryDetailDTO {

    private String name;
    private String capital;
    private String iso2;
    private double population;
    private double pop_growth;
    private Currency currency;

    public CountryDetailDTO() {}

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("capital")
    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    @JsonProperty("iso")
    public String getIso2() {
        return iso2;
    }

    public void setIso2(String iso2) {
        this.iso2 = iso2;
    }

    @JsonProperty("population")
    public double getPopulation() {
        return population;
    }

    public void setPopulation(double population) {
        this.population = population;
    }

    @JsonProperty("population_growth")
    public double getPopGrowth() {
        return pop_growth;
    }

    public void setPopGrowth(double pop_growth) {
        this.pop_growth = pop_growth;
    }

    @JsonProperty("currency")
    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public static class Currency {
        private String code;
        private String name;

        public Currency() {}

        @JsonProperty("code")
        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        @JsonProperty("name")
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
