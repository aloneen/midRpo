package kz.seisen.mid_rpo.service.impl;

import kz.seisen.mid_rpo.dto.CountryDto;
import kz.seisen.mid_rpo.dto.ItemDto;
import kz.seisen.mid_rpo.mapper.CountryMapper;
import kz.seisen.mid_rpo.mapper.ItemMapper;
import kz.seisen.mid_rpo.repository.CountryRepository;
import kz.seisen.mid_rpo.repository.ItemRepository;
import kz.seisen.mid_rpo.service.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;


@Service
@RequiredArgsConstructor
public class CountryServiceImpl implements CountryService {
    private final CountryRepository repository;
    private final CountryMapper mapper;


    @Override
    public List<CountryDto> getAll() {
        return mapper.toDtoList(repository.findAll());
    }

    @Override
    public CountryDto getById(Long id) {
        return mapper.toDto(repository.findById(id).orElse(null));
    }

    @Override
    public boolean create(CountryDto dto) {
        if (Objects.isNull(dto)) return false;

        repository.save(mapper.toEntity(dto));
        return true;
    }

    @Override
    public boolean update(Long id, CountryDto dto) {
        CountryDto oldCountry = getById(id);

        if (Objects.isNull(oldCountry) || Objects.isNull(dto)) {
            return false;
        }

        oldCountry.setNameDto(dto.getNameDto());
        oldCountry.setCodeDto(dto.getCodeDto());


        repository.save(mapper.toEntity(oldCountry));

        return true;
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
