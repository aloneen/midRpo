package kz.seisen.mid_rpo.mapper;


import kz.seisen.mid_rpo.dto.CategoryDto;
import kz.seisen.mid_rpo.dto.CountryDto;
import kz.seisen.mid_rpo.entity.CategoryEntity;
import kz.seisen.mid_rpo.entity.CountryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    @Mapping(target = "nameDto", source = "name")
    CategoryDto toDto(CategoryEntity entity);

    @Mapping(target = "name", source = "nameDto")
    CategoryEntity toEntity(CategoryDto dto);

    List<CategoryDto> toDtoList(List<CategoryEntity> entities);
}
