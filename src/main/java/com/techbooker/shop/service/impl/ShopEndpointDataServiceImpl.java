package com.techbooker.shop.service.impl;

import com.techbooker.shop.dto.EndpointDataDto;
import com.techbooker.shop.model.EndpointData;
import com.techbooker.shop.model.Shop;
import com.techbooker.shop.repository.ShopEndpointDataRepository;
import com.techbooker.shop.repository.ShopRepository;
import com.techbooker.shop.service.ShopEndpointDataService;
import com.techbooker.shop.util.converter.EntityConverter;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ShopEndpointDataServiceImpl implements ShopEndpointDataService {
    private final ShopEndpointDataRepository shopEndpointDataRepository;
    private final EntityConverter entityConverter;

    @Override
    public List<EndpointDataDto> findAll() {
        List<EndpointData> endpointDataList = shopEndpointDataRepository.findAll();
        return entityConverter.convert(endpointDataList, EndpointDataDto.class);
    }

    @Override
    public List<EndpointDataDto> findByShopId(Long shopId) {
        List<EndpointData> endpointDataList = shopEndpointDataRepository.findByShopId(shopId);
        return entityConverter.convert(endpointDataList, EndpointDataDto.class);
    }
}
