package kz.seisen.mid_rpo.service;

import kz.seisen.mid_rpo.dto.CountryDto;
import kz.seisen.mid_rpo.dto.ItemDto;

import java.util.List;

public interface CountryService {
    List<CountryDto> getAll();
    CountryDto getById(Long id);
    boolean create(CountryDto dto);
    boolean update(Long id, CountryDto dto);
    void delete(Long id);
}
