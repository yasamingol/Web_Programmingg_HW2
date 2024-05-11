package NationalCountries.repository;

import NationalCountries.models.Country;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CountriesRepository {
    public static ArrayList<Country> countries= new ArrayList<>();
    public List<Country> findAllCountries(){
        return countries;
    }


}
