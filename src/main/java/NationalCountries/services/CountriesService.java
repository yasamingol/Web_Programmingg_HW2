package NationalCountries.services;

import NationalCountries.models.Country;
import NationalCountries.models.CountryDetailDTO;
import NationalCountries.models.ExternalCountries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountriesService {

    @Autowired
    private ExternalCountriesService externalCountriesService;

    public List<Country> findAllCountries() {
        ExternalCountries response = externalCountriesService.fetchCountries();
        if (response != null && response.getCountries() != null) {
            return response.getCountries();
        }
        return List.of();
    }

    public CountryDetailDTO findCountryByName(String name) {
        return externalCountriesService.fetchCountryByName(name);
    }
}
