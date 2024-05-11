package NationalCountries.controllers;

import NationalCountries.models.Country;
import NationalCountries.models.CountryDetailDTO;
import NationalCountries.services.CountriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/countries")
public class CountriesController {

    @Autowired
    private CountriesService countriesService;

    @GetMapping("")
    public Map<String, Object> listAllCountries() {
        List<Country> countries = countriesService.findAllCountries();
        Map<String, Object> response = new HashMap<>();
        response.put("countries", countries);
        response.put("count", countries.size());
        return response;
    }

    @GetMapping("/{name}")
    public ResponseEntity<CountryDetailDTO> getCountryByName(@PathVariable String name) {
        CountryDetailDTO countryDetail = countriesService.findCountryByName(name);
        if (countryDetail != null) {
            return ResponseEntity.ok(countryDetail);
        } else {
            System.out.println("Country not found for name: " + name);
            return ResponseEntity.notFound().build();
        }
    }
}
