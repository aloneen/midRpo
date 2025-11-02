package kz.seisen.mid_rpo;


import kz.seisen.mid_rpo.dto.CategoryDto;
import kz.seisen.mid_rpo.dto.CountryDto;
import kz.seisen.mid_rpo.dto.ItemDto;
import kz.seisen.mid_rpo.entity.CategoryEntity;
import kz.seisen.mid_rpo.entity.CountryEntity;
import kz.seisen.mid_rpo.entity.ItemEntity;
import kz.seisen.mid_rpo.mapper.ItemMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class ItemMapperTest {


    @Autowired
    private ItemMapper itemMapper;




    @Test
    void convertEntityToDtoTest() {
        CategoryEntity categoryEntity = new CategoryEntity(1L, "Electronics");
        CountryEntity countryEntity = new CountryEntity(1L, "Kazakhstan", "KZ");


        ItemEntity itemEntity = new ItemEntity(1L, "Product", 1200, countryEntity, List.of(categoryEntity));

        ItemDto dto = itemMapper.toDto(itemEntity);

        Assertions.assertNotNull(dto);

        Assertions.assertNotNull(dto.getId());
        Assertions.assertNotNull(dto.getNameDto());
        Assertions.assertNotNull(dto.getPriceDto());
        Assertions.assertNotNull(dto.getCountryDto());
        Assertions.assertNotNull(dto.getCategoriesDto());

        Assertions.assertEquals(itemEntity.getId(), dto.getId());
        Assertions.assertEquals(itemEntity.getName(), dto.getNameDto());
        Assertions.assertEquals(itemEntity.getPrice(), dto.getPriceDto());
        // Comparing Country fields instead of objects (Entity vs Dto)
        Assertions.assertEquals(itemEntity.getCountry().getId(), dto.getCountryDto().getId());
        Assertions.assertEquals(itemEntity.getCountry().getName(), dto.getCountryDto().getNameDto());
        Assertions.assertEquals(itemEntity.getCountry().getCode(), dto.getCountryDto().getCodeDto());
        // Comparing Categories fields
        Assertions.assertEquals(itemEntity.getCategories().size(), dto.getCategoriesDto().size());
        for (int i = 0; i < itemEntity.getCategories().size(); i++) {
            Assertions.assertEquals(itemEntity.getCategories().get(i).getId(), dto.getCategoriesDto().get(i).getId());
            Assertions.assertEquals(itemEntity.getCategories().get(i).getName(), dto.getCategoriesDto().get(i).getNameDto());
        }

    }


    @Test
    void convertDtoToEntityTest() {
        CategoryDto categoryEntity = new CategoryDto(1L, "Electronics");
        CategoryDto categoryEntity2 = new CategoryDto(2L, "Tech");
        List<CategoryDto> categoryDtos = new ArrayList<>();
        categoryDtos.add(categoryEntity);
        categoryDtos.add(categoryEntity2);

        CountryDto countryDto = new CountryDto(1L, "Kazakhstan", "KZ");

        ItemDto itemDto = new ItemDto(1L, "Product", 1200, countryDto, categoryDtos);


        ItemEntity entity = itemMapper.toEntity(itemDto);

        Assertions.assertNotNull(entity);


        Assertions.assertNotNull(entity.getId());
        Assertions.assertNotNull(entity.getName());
        Assertions.assertNotNull(entity.getPrice());
        Assertions.assertNotNull(entity.getCountry());
        Assertions.assertNotNull(entity.getCategories());

        Assertions.assertEquals(entity.getId(), itemDto.getId());
        Assertions.assertEquals(entity.getName(), itemDto.getNameDto());
        Assertions.assertEquals(entity.getPrice(), itemDto.getPriceDto());
        // Comparing Country fields instead of objects (Entity vs Dto)
        Assertions.assertEquals(entity.getCountry().getId(), itemDto.getCountryDto().getId());
        Assertions.assertEquals(entity.getCountry().getName(), itemDto.getCountryDto().getNameDto());
        Assertions.assertEquals(entity.getCountry().getCode(), itemDto.getCountryDto().getCodeDto());
        // Comparing Categories fields
        Assertions.assertEquals(entity.getCategories().size(), itemDto.getCategoriesDto().size());
        for (int i = 0; i < entity.getCategories().size(); i++) {
            Assertions.assertEquals(entity.getCategories().get(i).getId(), itemDto.getCategoriesDto().get(i).getId());
            Assertions.assertEquals(entity.getCategories().get(i).getName(), itemDto.getCategoriesDto().get(i).getNameDto());
        }

    }



    @Test
    void convertEntityListToDtoListTest() {
        CategoryEntity categoryEntity = new CategoryEntity(1L, "Electronics");
        CategoryEntity categoryEntity2 = new CategoryEntity(2L, "Tech");
        List<CategoryEntity> categoryEntities = new ArrayList<>();
        categoryEntities.add(categoryEntity);
        categoryEntities.add(categoryEntity2);

        CountryEntity countryEntity = new CountryEntity(1L, "Kazakhstan", "KZ");

        List<ItemEntity> itemEntities = new ArrayList<>();

        itemEntities.add(new ItemEntity(1L, "1", 123, countryEntity, categoryEntities));
        itemEntities.add(new ItemEntity(2L, "2", 123, countryEntity, categoryEntities));
        itemEntities.add(new ItemEntity(3L, "3", 123, countryEntity, categoryEntities));

        List<ItemDto> itemDtoList = itemMapper.toDtoList(itemEntities);

        Assertions.assertNotNull(itemDtoList);

        Assertions.assertNotEquals(0, itemDtoList.size());

        Assertions.assertEquals(itemEntities.size(), itemDtoList.size());

        for ( int i = 0; i < itemDtoList.size(); i++) {


            ItemEntity itemEntity = itemEntities.get(i);

            ItemDto itemDto = itemDtoList.get(i);

            Assertions.assertNotNull(itemDto);

            Assertions.assertNotNull(itemDto.getId());
            Assertions.assertNotNull(itemDto.getNameDto());
            Assertions.assertNotNull(itemDto.getPriceDto());
            Assertions.assertNotNull(itemDto.getCountryDto());
            Assertions.assertNotNull(itemDto.getCategoriesDto());


            Assertions.assertEquals(itemDto.getId(), itemEntity.getId());
            Assertions.assertEquals(itemDto.getNameDto(), itemEntity.getName());
            Assertions.assertEquals(itemDto.getPriceDto(), itemEntity.getPrice());
            // Comparing Country fields instead of objects (Entity vs Dto)
            Assertions.assertEquals(itemDto.getCountryDto().getId(), itemEntity.getCountry().getId());
            Assertions.assertEquals(itemDto.getCountryDto().getNameDto(), itemEntity.getCountry().getName());
            Assertions.assertEquals(itemDto.getCountryDto().getCodeDto(), itemEntity.getCountry().getCode());
            // Comparing Categories fields
            Assertions.assertEquals(itemDto.getCategoriesDto().size(), itemEntity.getCategories().size());
            for (int j = 0; j < itemDto.getCategoriesDto().size(); j++) {
                Assertions.assertEquals(itemDto.getCategoriesDto().get(j).getId(), itemEntity.getCategories().get(j).getId());
                Assertions.assertEquals(itemDto.getCategoriesDto().get(j).getNameDto(), itemEntity.getCategories().get(j).getName());
            }



        }
    }
}
