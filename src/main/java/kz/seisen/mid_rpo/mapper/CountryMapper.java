package kz.seisen.mid_rpo.mapper;


import kz.seisen.mid_rpo.dto.CountryDto;
import kz.seisen.mid_rpo.dto.ItemDto;
import kz.seisen.mid_rpo.entity.CountryEntity;
import kz.seisen.mid_rpo.entity.ItemEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CountryMapper {
    @Mapping(target = "nameDto", source = "name")
    @Mapping(target = "codeDto", source = "code")
    CountryDto toDto(CountryEntity entity);

    @Mapping(target = "name", source = "nameDto")
    @Mapping(target = "code", source = "codeDto")
    CountryEntity toEntity(CountryDto dto);

    List<CountryDto> toDtoList(List<CountryEntity> entities);
}
