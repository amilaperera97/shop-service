package com.techbooker.shop.service.impl;

import com.techbooker.shop.dto.EndPointParamDto;
import com.techbooker.shop.dto.EndpointDataDto;
import com.techbooker.shop.dto.ShopDataDto;
import com.techbooker.shop.model.*;
import com.techbooker.shop.repository.*;
import com.techbooker.shop.service.ShopEndpointDataService;
import com.techbooker.shop.util.converter.EntityConverter;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ShopEndpointDataServiceImpl implements ShopEndpointDataService {
    private final ShopEndpointDataRepository shopEndpointDataRepository;
    private final ShopRepository shopRepository;
    private final InternalParamRepository internalParamRepository;
    private final ParamDataTypeRepository paramDataTypeRepository;
    private final ExternalParamRepository externalParamRepository;
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

    @Override
    public EndpointDataDto save(EndpointDataDto endpointData) {
        Optional<Shop> shopOptional = shopRepository.findById(endpointData.getShopId());
        //TODO Throw custom not found exception
//        shopOptional.orElseThrow(()->new Notf)

        EndpointData endpointDataModel = entityConverter.convert(endpointData, EndpointData.class);
        endpointDataModel.setShop(shopOptional.get());
        endpointDataModel.setLastUpdatedUser(endpointData.getUser().getUsername());
        endpointDataModel.setCreatedUser(endpointData.getUser().getUsername());

        List<ExternalParamModel> externalParamModels = new ArrayList<>();

        for (EndPointParamDto endPointParam : endpointData.getEndPointParams()) {
            Optional<ParamDataType> paramDataType = paramDataTypeRepository.findById(endPointParam.getExternalParamData().getDataType());

            if (paramDataType.isPresent()) {
                Optional<InternalParamModel> internalParamModel = internalParamRepository.findByParamName(endPointParam.getInternalParamName());

                ExternalParamModel externalParamModel = entityConverter.convert(endPointParam.getExternalParamData(), ExternalParamModel.class);
                externalParamModel.setInternalParam(internalParamModel.isPresent() ? internalParamModel.get() : null);
                externalParamModel.setDataType(paramDataType.get());

                externalParamModel.setLastUpdatedUser(endpointData.getUser().getUsername());
                externalParamModel.setCreatedUser(endpointData.getUser().getUsername());

                externalParamModels.add(externalParamModel);
            }
        }
        endpointDataModel.setEndPointParams(externalParamModels);
        EndpointData savedEntity = shopEndpointDataRepository.save(endpointDataModel);

        savedEntity.getEndPointParams().stream()
                .filter(endpoint -> endpoint.getEndpointData() == null)
                .forEach(endpoint -> endpoint.setEndpointData(savedEntity));

        externalParamRepository.saveAll(savedEntity.getEndPointParams());

        ShopDataDto shopData = entityConverter.convert(shopOptional.get(), ShopDataDto.class);
        endpointData.setShop(shopData);
        return endpointData;
    }
}
