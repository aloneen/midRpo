package kz.seisen.mid_rpo.service;


import kz.seisen.mid_rpo.dto.CategoryDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Random;

@SpringBootTest
public class CategoryServiceTest {

    @Autowired
    private CategoryService categoryService;

    @Test
    void getAllTest() {
        List<CategoryDto> list = categoryService.getAll();

        Assertions.assertNotNull(list);
        Assertions.assertNotEquals(0, list.size());

        for (CategoryDto dto : list) {
            Assertions.assertNotNull(dto);
            Assertions.assertNotNull(dto.getId());
            Assertions.assertNotNull(dto.getNameDto());
        }
    }

    @Test
    void getByIdTest() {
        Random random = new Random();
        int randomIndex = random.nextInt(categoryService.getAll().size());
        Long someIndex = categoryService.getAll().get(randomIndex).getId();

        CategoryDto categoryDto = categoryService.getById(someIndex);
        Assertions.assertNotNull(categoryDto);
        Assertions.assertNotNull(categoryDto.getId());
        Assertions.assertNotNull(categoryDto.getNameDto());

        CategoryDto missing = categoryService.getById(-1L);
        Assertions.assertNull(missing);
    }

    @Test
    void addTest() {
        int before = categoryService.getAll().size();

        CategoryDto categoryDto = new CategoryDto(null, "New Category");

        Assertions.assertTrue(categoryService.create(categoryDto));

        int after = categoryService.getAll().size();
        Assertions.assertEquals(before + 1, after);
    }

    @Test
    void updateTest() {
        Random random = new Random();
        int randomIndex = random.nextInt(categoryService.getAll().size());
        Long someIndex = categoryService.getAll().get(randomIndex).getId();

        CategoryDto current = categoryService.getById(someIndex);
        CategoryDto dto = new CategoryDto(someIndex, "Updated Category");

        boolean updated = categoryService.update(dto.getId(), dto);
        Assertions.assertTrue(updated);

        CategoryDto updatedCategory = categoryService.getById(someIndex);
        Assertions.assertNotNull(updatedCategory);
        Assertions.assertNotNull(updatedCategory.getId());
        Assertions.assertNotNull(updatedCategory.getNameDto());

        Assertions.assertEquals(dto.getId(), updatedCategory.getId());
        Assertions.assertEquals(dto.getNameDto(), updatedCategory.getNameDto());
    }

    @Test
    void deleteTest() {
        int before = categoryService.getAll().size();

        Random random = new Random();
        int randomIndex = random.nextInt(categoryService.getAll().size());
        Long someIndex = categoryService.getAll().get(randomIndex).getId();

        categoryService.delete(someIndex);

        CategoryDto deleted = categoryService.getById(someIndex);
        Assertions.assertNull(deleted);

        int after = categoryService.getAll().size();
        Assertions.assertEquals(before - 1, after);
    }
}


