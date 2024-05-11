// NationalCountries/services/CountriesService.java
package NationalCountries.services;

import NationalCountries.models.CountryDTO;
import NationalCountries.models.ExternalCountry;
import NationalCountries.thirdparty.ExternalCountriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CountriesService {

    @Autowired
    private ExternalCountriesService externalCountriesService;

    public List<CountryDTO> findAllCountries() {
        var response = externalCountriesService.fetchCountries();
        if (response != null && response.getCountries() != null) {
            return response.getCountries().stream()
                    .map(externalCountry -> new CountryDTO(externalCountry.getName()))
                    .collect(Collectors.toList());
        }
        return List.of();
    }
}
