// NationalCountries/controllers/CountriesController.java
package NationalCountries.controllers;

import NationalCountries.models.CountryDTO;
import NationalCountries.services.CountriesService;
import org.springframework.beans.factory.annotation.Autowired;
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
        List<CountryDTO> countries = countriesService.findAllCountries();
        Map<String, Object> response = new HashMap<>();
        response.put("countries", countries);
        response.put("count", countries.size());
        return response;
    }
}
