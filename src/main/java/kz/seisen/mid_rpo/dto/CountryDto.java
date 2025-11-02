package kz.seisen.mid_rpo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CountryDto {

    private Long id;
    private String nameDto;
    private String codeDto;
}
