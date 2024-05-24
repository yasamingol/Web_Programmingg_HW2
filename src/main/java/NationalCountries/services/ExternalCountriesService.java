package NationalCountries.services;

import NationalCountries.dto.CountryDetailDTO;
import NationalCountries.dto.ExternalCountriesDTO;
import NationalCountries.dto.WeatherInfoDTO;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
public class ExternalCountriesService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${countries.external.api.url}")
    private String countriesExternalApiUrl;

    @Value("${api.key}")
    private String apiKey;

    @Value("${country.data.external.api.url}")
    private String countryDataExternalApiUrl;

    @Value("${weather.api.url}")
    private String weatherApiUrl;


    //todo: remove these to a thirdPartyServices package and make them clean.
    public ExternalCountriesDTO fetchCountries() {
        return restTemplate.getForObject(countriesExternalApiUrl, ExternalCountriesDTO.class);
    }

    public CountryDetailDTO fetchCountryByName(String name) {
        CountryDetailDTO countryDetail = null;
        try {
            URL url = new URL(countryDataExternalApiUrl + name.replace(" ", "%20"));
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("X-Api-Key", apiKey);
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
            URL url = new URL(weatherApiUrl + city.replace(" ", "%20"));
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("X-Api-Key", apiKey);
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
