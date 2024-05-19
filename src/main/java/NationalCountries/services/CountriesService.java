package NationalCountries.services;

import NationalCountries.models.Country;
import NationalCountries.dto.CountryDetailDTO;
import NationalCountries.models.ExternalCountries;
import NationalCountries.dto.WeatherInfoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountriesService {

    @Autowired
    private ExternalCountriesService externalCountriesService;

    @Cacheable(value = "countries")
    public List<Country> findAllCountries() {
        ExternalCountries response = externalCountriesService.fetchCountries();
        if (response != null && response.getCountries() != null) {
            return response.getCountries();
        }
        return List.of();
    }

    @Cacheable(value = "countryDetail", key = "#name")
    public CountryDetailDTO findCountryByName(String name) {
        return externalCountriesService.fetchCountryByName(name);
    }


    @Cacheable(value = "weatherInfo", key = "#name")
    public WeatherInfoDTO findCountriesCapitalCityWeather(String name) {
        return externalCountriesService.fetchWeatherForCapital(name);
    }
}
