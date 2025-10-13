package kz.seisen.mid_rpo.service;

import kz.seisen.mid_rpo.dto.ItemDto;

import java.util.List;

public interface ItemService {

    List<ItemDto> getAll();
    ItemDto getById(Long id);
    boolean create(ItemDto dto);
    boolean update(Long id, ItemDto dto);
    void delete(Long id);
}
