package kz.seisen.mid_rpo.service.impl;


import kz.seisen.mid_rpo.dto.ItemDto;
import kz.seisen.mid_rpo.mapper.ItemMapper;
import kz.seisen.mid_rpo.repository.ItemRepository;
import kz.seisen.mid_rpo.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final ItemRepository repository;
    private final ItemMapper mapper;


    @Override
    public List<ItemDto> getAll() {
        return mapper.toDtoList(repository.findAll());
    }

    @Override
    public ItemDto getById(Long id) {
        return mapper.toDto(repository.findById(id).orElse(null));
    }

    @Override
    public boolean create(ItemDto dto) {
        if (Objects.isNull(dto)) return false;

        repository.save(mapper.toEntity(dto));
        return true;
    }

    @Override
    public boolean update(Long id, ItemDto dto) {
        ItemDto oldItem = getById(id);

        if (Objects.isNull(oldItem) || Objects.isNull(dto)) {
            return false;
        }

        oldItem.setNameDto(dto.getNameDto());
        oldItem.setPriceDto(dto.getPriceDto());
        oldItem.setCountryDto(dto.getCountryDto());
        oldItem.setCategoriesDto(dto.getCategoriesDto());

        repository.save(mapper.toEntity(oldItem));

        return true;
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
