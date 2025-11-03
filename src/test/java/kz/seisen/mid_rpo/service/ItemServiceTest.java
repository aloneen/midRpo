package kz.seisen.mid_rpo.service;


import kz.seisen.mid_rpo.dto.CategoryDto;
import kz.seisen.mid_rpo.dto.CountryDto;
import kz.seisen.mid_rpo.dto.ItemDto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.List;
import java.util.Random;

@SpringBootTest
public class ItemServiceTest {

    @Autowired
    private ItemService itemService;



    @Test
    void getAllTest() {
        List<ItemDto> itemDtoList = itemService.getAll();


        Assertions.assertNotNull(itemDtoList);

        Assertions.assertNotEquals(0, itemDtoList.size());

        for (int i = 0; i < itemDtoList.size(); i++) {

            ItemDto dto = itemDtoList.get(i);

            Assertions.assertNotNull(dto);

            Assertions.assertNotNull(dto.getId());
            Assertions.assertNotNull(dto.getNameDto());
            Assertions.assertNotNull(dto.getPriceDto());
            Assertions.assertNotNull(dto.getCountryDto());
            Assertions.assertNotNull(dto.getCategoriesDto());
        }
    }

    @Test
    void getByIdTest() {
            Random random = new Random();
            int randomIndex = random.nextInt(itemService.getAll().size());
            Long someIndex = itemService.getAll().get(randomIndex).getId();



            ItemDto itemDto = itemService.getById(someIndex);

            Assertions.assertNotNull(itemDto);

            Assertions.assertNotNull(itemDto.getId());
            Assertions.assertNotNull(itemDto.getNameDto());
            Assertions.assertNotNull(itemDto.getPriceDto());
            Assertions.assertNotNull(itemDto.getCountryDto());
            Assertions.assertNotNull(itemDto.getCategoriesDto());


            ItemDto check = itemService.getById(-1L);
            Assertions.assertNull(check);

    }

    @Test
    void addTest() {
        int before = itemService.getAll().size();

        CategoryDto categoryDto = new CategoryDto(1L, "name");
        CountryDto countryDto = new CountryDto(1L, "name", "name");

        ItemDto itemDto = new ItemDto(null, "name", 2399, countryDto, List.of(categoryDto));

        Assertions.assertTrue(itemService.create(itemDto));

        int after = itemService.getAll().size();
        Assertions.assertEquals(before + 1, after);
    }


    @Test
    void updateTest() {
        Random random = new Random();
        int randomIndex = random.nextInt(itemService.getAll().size());
        Long someIndex = itemService.getAll().get(randomIndex).getId();


        //current item
        ItemDto current = itemService.getById(someIndex);
        //new item
        ItemDto itemDto = new ItemDto( someIndex, "Name", 133, current.getCountryDto(), current.getCategoriesDto());

        boolean updated = itemService.update(itemDto.getId(), itemDto);
        Assertions.assertTrue(updated);

        //checking
        ItemDto updatedItem = itemService.getById(someIndex);

        Assertions.assertNotNull(updatedItem);

        Assertions.assertNotNull(updatedItem.getId());
        Assertions.assertNotNull(updatedItem.getPriceDto());
        Assertions.assertNotNull(updatedItem.getNameDto());
        Assertions.assertNotNull(updatedItem.getCountryDto());
        Assertions.assertNotNull(updatedItem.getCategoriesDto());


        Assertions.assertEquals(itemDto.getId(), updatedItem.getId());
        Assertions.assertEquals(itemDto.getNameDto(), updatedItem.getNameDto());
        Assertions.assertEquals(itemDto.getPriceDto(), updatedItem.getPriceDto());
        Assertions.assertEquals(itemDto.getCountryDto(), updatedItem.getCountryDto());
        Assertions.assertEquals(itemDto.getCategoriesDto(), updatedItem.getCategoriesDto());
    }




    @Test
    void deleteTest() {
        int before = itemService.getAll().size();

        Random random = new Random();
        int randomIndex = random.nextInt(itemService.getAll().size());
        Long someIndex = itemService.getAll().get(randomIndex).getId();


        itemService.delete(someIndex);


        ItemDto deleted = itemService.getById(someIndex);
        Assertions.assertNull(deleted);


        int after = itemService.getAll().size();

        Assertions.assertEquals(before - 1, after);
    }


}
