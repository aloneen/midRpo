package kz.seisen.mid_rpo.mapper;

import kz.seisen.mid_rpo.dto.CategoryDto;
import kz.seisen.mid_rpo.entity.CategoryEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class CategoryMapperTest {

    @Autowired
    private CategoryMapper categoryMapper;

    @Test
    void convertEntityToDtoTest() {
        CategoryEntity categoryEntity = new CategoryEntity(1L, "Electronics");

        CategoryDto dto = categoryMapper.toDto(categoryEntity);

        Assertions.assertNotNull(dto);
        Assertions.assertNotNull(dto.getId());
        Assertions.assertNotNull(dto.getNameDto());

        Assertions.assertEquals(categoryEntity.getId(), dto.getId());
        Assertions.assertEquals(categoryEntity.getName(), dto.getNameDto());
    }

    @Test
    void convertDtoToEntityTest() {
        CategoryDto categoryDto = new CategoryDto(1L, "Electronics");

        CategoryEntity entity = categoryMapper.toEntity(categoryDto);

        Assertions.assertNotNull(entity);
        Assertions.assertNotNull(entity.getId());
        Assertions.assertNotNull(entity.getName());

        Assertions.assertEquals(entity.getId(), categoryDto.getId());
        Assertions.assertEquals(entity.getName(), categoryDto.getNameDto());
    }

    @Test
    void convertEntityListToDtoListTest() {
        List<CategoryEntity> categoryEntities = new ArrayList<>();
        categoryEntities.add(new CategoryEntity(1L, "Electronics"));
        categoryEntities.add(new CategoryEntity(2L, "Tech"));
        categoryEntities.add(new CategoryEntity(3L, "Gadgets"));

        List<CategoryDto> categoryDtoList = categoryMapper.toDtoList(categoryEntities);

        Assertions.assertNotNull(categoryDtoList);
        Assertions.assertNotEquals(0, categoryDtoList.size());
        Assertions.assertEquals(categoryEntities.size(), categoryDtoList.size());

        for (int i = 0; i < categoryDtoList.size(); i++) {
            CategoryEntity categoryEntity = categoryEntities.get(i);
            CategoryDto categoryDto = categoryDtoList.get(i);

            Assertions.assertNotNull(categoryDto);
            Assertions.assertNotNull(categoryDto.getId());
            Assertions.assertNotNull(categoryDto.getNameDto());

            Assertions.assertEquals(categoryDto.getId(), categoryEntity.getId());
            Assertions.assertEquals(categoryDto.getNameDto(), categoryEntity.getName());
        }
    }
}

