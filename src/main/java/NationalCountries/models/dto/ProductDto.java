package NationalCountries.models.dto;

import NationalCountries.models.CountryDTO;

public class ProductDto {
    public String title;
    public String brand;
    public int price;

    public ProductDto(CountryDTO product) {
        this.title = product.getTitle();
        this.brand = product.getBrand();
        this.price = product.getPrice();
    }


}
