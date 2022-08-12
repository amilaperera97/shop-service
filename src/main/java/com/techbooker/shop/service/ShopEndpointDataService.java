package com.techbooker.shop.service;

import com.techbooker.shop.dto.EndpointDataDto;

import java.util.List;

public interface ShopEndpointDataService {

    List<EndpointDataDto> findAll();
    List<EndpointDataDto> findByShopId(Long shopId);

    EndpointDataDto save(EndpointDataDto endpointData);
}
