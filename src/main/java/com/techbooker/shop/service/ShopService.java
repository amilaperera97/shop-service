package com.techbooker.shop.service;

import com.techbooker.shop.dto.ShopDataDto;

import java.util.List;

public interface ShopService {
    ShopDataDto save(ShopDataDto data);
    ShopDataDto update(ShopDataDto data);
    void delete(ShopDataDto data);
    List<ShopDataDto> findAll();
    ShopDataDto findById(Long id);
}
