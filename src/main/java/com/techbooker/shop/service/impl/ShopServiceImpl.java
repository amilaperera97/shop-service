package com.techbooker.shop.service.impl;

import com.techbooker.shop.dto.BranchDataDto;
import com.techbooker.shop.dto.ShopDataDto;
import com.techbooker.shop.exception.custom.ResourceNotFoundException;
import com.techbooker.shop.model.Shop;
import com.techbooker.shop.repository.ShopRepository;
import com.techbooker.shop.service.ShopService;
import com.techbooker.shop.util.converter.EntityConverter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ShopServiceImpl implements ShopService {
    private final ShopRepository shopRepository;
    private final EntityConverter entityConverter;
    private final MessageSource messageSource;

    @Override
    public ShopDataDto save(ShopDataDto data) {
        Shop shop = entityConverter.convert(data, Shop.class);
        shop.setCreatedUser(data.getUser().getUsername());
        shop.setLastUpdatedUser(data.getUser().getUsername());

        Shop savedData = shopRepository.save(shop);
        log.info("Saved Data : {}", savedData);
        return entityConverter.convert(savedData, ShopDataDto.class);
    }

    @Override
    public ShopDataDto update(ShopDataDto data) {
        Shop shop = entityConverter.convert(data, Shop.class);
        shop.setLastUpdatedUser(data.getUser().getUsername());

        Shop updatedData = shopRepository.saveAndFlush(shop);
        log.info("Updated Data : {}", updatedData);
        return entityConverter.convert(updatedData, ShopDataDto.class);
    }

    @Override
    public void delete(ShopDataDto data) {
        shopRepository.findById(data.getId()).ifPresent(this::delete);
    }

    @Override
    public List<ShopDataDto> findAll() {
        List<Shop> shopList = shopRepository.findAll().stream()
                .filter(shop -> !shop.getIsDeleted()).collect(Collectors.toList());
        return entityConverter.convert(shopList, ShopDataDto.class);
    }

    @Override
    public ShopDataDto findById(Long id) throws ResourceNotFoundException {
        Optional<Shop> shop = shopRepository.findById(id).filter(shopInfo -> !shopInfo.getIsDeleted());

        if (shop.isPresent()) {
            return entityConverter.convert(shop.get(), ShopDataDto.class);
        }
        throw new ResourceNotFoundException(HttpStatus.NOT_FOUND,
                messageSource.getMessage("no.records.matches", new Object[0], Locale.ENGLISH));
    }

    private void delete(Shop shop) {
        shop.setIsDeleted(true);
        shopRepository.saveAndFlush(shop);
    }
}
