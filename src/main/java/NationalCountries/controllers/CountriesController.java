package NationalCountries.controllers;

import NationalCountries.models.CountryDTO;
import NationalCountries.services.CountriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/countries")
public class CountriesController {

    @Autowired
    private CountriesService countriesService;

    @GetMapping("")
    public List<CountryDTO> listAllCountries(){
        return countriesService.findAllCountries().stream().map(country -> new CountryDTO(country)).toList();
    }

}
