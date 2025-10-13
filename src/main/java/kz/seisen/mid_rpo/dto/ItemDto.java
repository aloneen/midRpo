package kz.seisen.mid_rpo.dto;


import lombok.Data;

import java.util.List;

@Data
public class ItemDto {
    private Long id;
    private String nameDto;
    private int priceDto;


    private CountryDto countryDto;
    private List<CategoryDto> categoriesDto;
}
