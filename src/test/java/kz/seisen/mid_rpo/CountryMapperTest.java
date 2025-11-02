package kz.seisen.mid_rpo;

import kz.seisen.mid_rpo.dto.CountryDto;
import kz.seisen.mid_rpo.entity.CountryEntity;
import kz.seisen.mid_rpo.mapper.CountryMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class CountryMapperTest {

    @Autowired
    private CountryMapper countryMapper;

    @Test
    void convertEntityToDtoTest() {
        CountryEntity countryEntity = new CountryEntity(1L, "Kazakhstan", "KZ");

        CountryDto dto = countryMapper.toDto(countryEntity);

        Assertions.assertNotNull(dto);
        Assertions.assertNotNull(dto.getId());
        Assertions.assertNotNull(dto.getNameDto());
        Assertions.assertNotNull(dto.getCodeDto());

        Assertions.assertEquals(countryEntity.getId(), dto.getId());
        Assertions.assertEquals(countryEntity.getName(), dto.getNameDto());
        Assertions.assertEquals(countryEntity.getCode(), dto.getCodeDto());
    }

    @Test
    void convertDtoToEntityTest() {
        CountryDto countryDto = new CountryDto(1L, "Kazakhstan", "KZ");

        CountryEntity entity = countryMapper.toEntity(countryDto);

        Assertions.assertNotNull(entity);
        Assertions.assertNotNull(entity.getId());
        Assertions.assertNotNull(entity.getName());
        Assertions.assertNotNull(entity.getCode());

        Assertions.assertEquals(entity.getId(), countryDto.getId());
        Assertions.assertEquals(entity.getName(), countryDto.getNameDto());
        Assertions.assertEquals(entity.getCode(), countryDto.getCodeDto());
    }

    @Test
    void convertEntityListToDtoListTest() {
        List<CountryEntity> countryEntities = new ArrayList<>();
        countryEntities.add(new CountryEntity(1L, "Kazakhstan", "KZ"));
        countryEntities.add(new CountryEntity(2L, "Russia", "RU"));
        countryEntities.add(new CountryEntity(3L, "USA", "US"));

        List<CountryDto> countryDtoList = countryMapper.toDtoList(countryEntities);

        Assertions.assertNotNull(countryDtoList);
        Assertions.assertNotEquals(0, countryDtoList.size());
        Assertions.assertEquals(countryEntities.size(), countryDtoList.size());

        for (int i = 0; i < countryDtoList.size(); i++) {
            CountryEntity countryEntity = countryEntities.get(i);
            CountryDto countryDto = countryDtoList.get(i);

            Assertions.assertNotNull(countryDto);
            Assertions.assertNotNull(countryDto.getId());
            Assertions.assertNotNull(countryDto.getNameDto());
            Assertions.assertNotNull(countryDto.getCodeDto());

            Assertions.assertEquals(countryDto.getId(), countryEntity.getId());
            Assertions.assertEquals(countryDto.getNameDto(), countryEntity.getName());
            Assertions.assertEquals(countryDto.getCodeDto(), countryEntity.getCode());
        }
    }
}

