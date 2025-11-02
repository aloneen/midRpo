package kz.seisen.mid_rpo.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ItemDto {
    private Long id;
    private String nameDto;
    private int priceDto;


    private CountryDto countryDto;
    private List<CategoryDto> categoriesDto;
}
