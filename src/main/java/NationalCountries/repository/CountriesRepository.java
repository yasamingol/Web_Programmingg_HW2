package NationalCountries.repository;

import NationalCountries.models.CountryDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CountriesRepository {
    public static ArrayList<CountryDTO> countries= new ArrayList<>();
    public List<CountryDTO> findAllCountries(){
        return countries;
    }


}
