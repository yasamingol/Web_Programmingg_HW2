package NationalCountries.services;

import NationalCountries.models.CountryDTO;
import NationalCountries.repository.CountriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountriesService {

    @Autowired
    private CountriesRepository countriesRepository;

    public List<CountryDTO> findAllCountries(){
        return countriesRepository.findAllCountries();
    }





}
