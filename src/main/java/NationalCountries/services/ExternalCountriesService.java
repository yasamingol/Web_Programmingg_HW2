package NationalCountries.services;

import NationalCountries.models.CountryDetailDTO;
import NationalCountries.models.ExternalCountries;
import NationalCountries.models.WeatherInfoDTO;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
public class ExternalCountriesService {

    @Autowired
    private RestTemplate restTemplate;

    //todo: remove these URL and KEYS to a config file later
    private static final String COUNTRIES_EXTERNAL_API_URL = "https://countriesnow.space/api/v0.1/countries";
    private static final String API_KEY = "8G8ii2K8okXT0Fbw1vMIwA==wswrBR17Nz8coIO1";
    private static final String COUNTRY_DATA_EXTERNAL_API_URL = "https://api.api-ninjas.com/v1/country?name=";
    private static final String WEATHER_API_URL = "https://api.api-ninjas.com/v1/weather?city=";


    //todo: remove these to a thirdPartyServices package and make them clean.
    public ExternalCountries fetchCountries() {
        return restTemplate.getForObject(COUNTRIES_EXTERNAL_API_URL, ExternalCountries.class);
    }

    public CountryDetailDTO fetchCountryByName(String name) {
        CountryDetailDTO countryDetail = null;
        try {
            URL url = new URL(COUNTRY_DATA_EXTERNAL_API_URL + name.replace(" ", "%20"));
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("X-Api-Key", API_KEY);
            connection.setRequestProperty("Accept", "application/json");

            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

            InputStream responseStream = connection.getInputStream();
            CountryDetailDTO[] results = mapper.readValue(responseStream, CountryDetailDTO[].class);
            if (results != null && results.length > 0) {
                countryDetail = results[0];
            }
            connection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return countryDetail;
    }

    public WeatherInfoDTO fetchWeatherForCapital(String countryName) {
        CountryDetailDTO country = fetchCountryByName(countryName);
        String capital = country.getCapital();
        WeatherInfoDTO weatherInfo = fetchWeather(capital);
        weatherInfo.setCountryName(countryName);
        weatherInfo.setCapital(capital);
        return weatherInfo;
    }


    private WeatherInfoDTO fetchWeather(String city) {
        try {
            URL url = new URL(WEATHER_API_URL + city.replace(" ", "%20"));
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("X-Api-Key", API_KEY);
            connection.setRequestProperty("Accept", "application/json");

            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

            InputStream responseStream = connection.getInputStream();
            WeatherInfoDTO result = mapper.readValue(responseStream, WeatherInfoDTO.class);
            connection.disconnect();

            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
