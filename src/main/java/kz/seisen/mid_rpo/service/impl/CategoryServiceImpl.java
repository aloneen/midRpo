package kz.seisen.mid_rpo.service.impl;


import kz.seisen.mid_rpo.dto.CategoryDto;
import kz.seisen.mid_rpo.dto.ItemDto;
import kz.seisen.mid_rpo.mapper.CategoryMapper;
import kz.seisen.mid_rpo.mapper.ItemMapper;
import kz.seisen.mid_rpo.repository.CategoryRepository;
import kz.seisen.mid_rpo.repository.ItemRepository;
import kz.seisen.mid_rpo.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository repository;
    private final CategoryMapper mapper;


    @Override
    public List<CategoryDto> getAll() {
        return mapper.toDtoList(repository.findAll());
    }

    @Override
    public CategoryDto getById(Long id) {
        return mapper.toDto(repository.findById(id).orElse(null));
    }

    @Override
    public boolean create(CategoryDto dto) {
        if (Objects.isNull(dto)) return false;

        repository.save(mapper.toEntity(dto));
        return true;
    }

    @Override
    public boolean update(Long id, CategoryDto dto) {
        CategoryDto oldCategory = getById(id);

        if (Objects.isNull(oldCategory) || Objects.isNull(dto)) {
            return false;
        }

        oldCategory.setNameDto(dto.getNameDto());

        repository.save(mapper.toEntity(oldCategory));

        return true;
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
