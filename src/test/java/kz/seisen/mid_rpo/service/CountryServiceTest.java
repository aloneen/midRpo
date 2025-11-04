package kz.seisen.mid_rpo.service;


import kz.seisen.mid_rpo.dto.CountryDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Random;

@SpringBootTest
public class CountryServiceTest {

    @Autowired
    private CountryService countryService;

    @Test
    void getAllTest() {
        List<CountryDto> list = countryService.getAll();

        Assertions.assertNotNull(list);
        Assertions.assertNotEquals(0, list.size());

        for (CountryDto dto : list) {
            Assertions.assertNotNull(dto);
            Assertions.assertNotNull(dto.getId());
            Assertions.assertNotNull(dto.getNameDto());
            Assertions.assertNotNull(dto.getCodeDto());
        }
    }

    @Test
    void getByIdTest() {
        Random random = new Random();
        int randomIndex = random.nextInt(countryService.getAll().size());
        Long someIndex = countryService.getAll().get(randomIndex).getId();

        CountryDto countryDto = countryService.getById(someIndex);
        Assertions.assertNotNull(countryDto);
        Assertions.assertNotNull(countryDto.getId());
        Assertions.assertNotNull(countryDto.getNameDto());
        Assertions.assertNotNull(countryDto.getCodeDto());

        CountryDto missing = countryService.getById(-1L);
        Assertions.assertNull(missing);
    }

    @Test
    void addTest() {
        int before = countryService.getAll().size();

        CountryDto countryDto = new CountryDto(null, "New Country", "NC");

        Assertions.assertTrue(countryService.create(countryDto));

        int after = countryService.getAll().size();
        Assertions.assertEquals(before + 1, after);
    }

    @Test
    void updateTest() {
        Random random = new Random();
        int randomIndex = random.nextInt(countryService.getAll().size());
        Long someIndex = countryService.getAll().get(randomIndex).getId();

        CountryDto current = countryService.getById(someIndex);
        CountryDto dto = new CountryDto(someIndex, "Updated Country", "UC");

        boolean updated = countryService.update(dto.getId(), dto);
        Assertions.assertTrue(updated);

        CountryDto updatedCountry = countryService.getById(someIndex);
        Assertions.assertNotNull(updatedCountry);
        Assertions.assertNotNull(updatedCountry.getId());
        Assertions.assertNotNull(updatedCountry.getNameDto());
        Assertions.assertNotNull(updatedCountry.getCodeDto());

        Assertions.assertEquals(dto.getId(), updatedCountry.getId());
        Assertions.assertEquals(dto.getNameDto(), updatedCountry.getNameDto());
        Assertions.assertEquals(dto.getCodeDto(), updatedCountry.getCodeDto());
    }

    @Test
    void deleteTest() {
        int before = countryService.getAll().size();

        Random random = new Random();
        int randomIndex = random.nextInt(countryService.getAll().size());
        Long someIndex = countryService.getAll().get(randomIndex).getId();

        countryService.delete(someIndex);

        CountryDto deleted = countryService.getById(someIndex);
        Assertions.assertNull(deleted);

        int after = countryService.getAll().size();
        Assertions.assertEquals(before - 1, after);
    }
}



