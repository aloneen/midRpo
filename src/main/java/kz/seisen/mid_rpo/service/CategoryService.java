package kz.seisen.mid_rpo.service;

import kz.seisen.mid_rpo.dto.CategoryDto;
import kz.seisen.mid_rpo.dto.ItemDto;

import java.util.List;

public interface CategoryService {
    List<CategoryDto> getAll();
    CategoryDto getById(Long id);
    boolean create(CategoryDto dto);
    boolean update(Long id, CategoryDto dto);
    void delete(Long id);
}
