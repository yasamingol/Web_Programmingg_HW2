package NationalCountries.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

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

    public WeatherInfoDTO() {
    }

    public WeatherInfoDTO(String countryName, String capital, double windSpeed, int windDegrees, int temp, int humidity) {
        this.countryName = countryName;
        this.capital = capital;
        this.windSpeed = windSpeed;
        this.windDegrees = windDegrees;
        this.temp = temp;
        this.humidity = humidity;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
    }

    public int getWindDegrees() {
        return windDegrees;
    }

    public void setWindDegrees(int windDegrees) {
        this.windDegrees = windDegrees;
    }

    public int getTemp() {
        return temp;
    }

    public void setTemp(int temp) {
        this.temp = temp;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }
}
