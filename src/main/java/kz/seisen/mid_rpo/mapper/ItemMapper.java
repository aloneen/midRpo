package kz.seisen.mid_rpo.mapper;


import kz.seisen.mid_rpo.dto.ItemDto;
import kz.seisen.mid_rpo.entity.ItemEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {CountryMapper.class, CategoryMapper.class})
public interface ItemMapper {


    @Mapping(target = "nameDto", source = "name")
    @Mapping(target = "priceDto", source = "price")
    @Mapping(target = "countryDto", source = "country")
    @Mapping(target = "categoriesDto", source = "categories")
    ItemDto toDto(ItemEntity entity);

    @Mapping(target = "name", source = "nameDto")
    @Mapping(target = "price", source = "priceDto")
    @Mapping(target = "country", source = "countryDto")
    @Mapping(target = "categories", source = "categoriesDto")
    ItemEntity toEntity(ItemDto dto);

    List<ItemDto> toDtoList(List<ItemEntity> entities);
}
