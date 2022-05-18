package com.techbooker.shop.service.impl;

import com.techbooker.shop.dto.ValidateBranchInfoDto;
import com.techbooker.shop.exception.custom.ResourceNotFoundException;
import com.techbooker.shop.model.ContactInfo;
import com.techbooker.shop.repository.BranchRepository;
import com.techbooker.shop.repository.ShopRepository;
import com.techbooker.shop.service.ValidationService;
import com.techbooker.shop.util.converter.EntityConverter;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicReference;

@Service
@Slf4j
@AllArgsConstructor
public class ValidationServiceImpl implements ValidationService {

    private final ShopRepository shopRepository;
    private final BranchRepository branchRepository;
    private final EntityConverter entityConverter;

    @Override
    public Boolean isValid(ValidateBranchInfoDto validateBranchInfo) throws ResourceNotFoundException {
        AtomicReference<Boolean> isValid = new AtomicReference<>(false);
        shopRepository.findShopByIdAndName(Long.parseLong(String.valueOf(validateBranchInfo.getShopId())), validateBranchInfo.getShopName()).ifPresentOrElse(shop -> {
            Long branchId = Long.parseLong(String.valueOf(validateBranchInfo.getBranchId()));
            branchRepository.findBranchByIdAndShopAndNameAndContactInfoCityAndContactInfoPostCodeAndIsDeletedFalse(branchId, shop, validateBranchInfo.getBranchName(), validateBranchInfo.getBranchContactInfo().getCity(), validateBranchInfo.getBranchContactInfo().getPostCode()).ifPresentOrElse(branch -> isValid.set(true), () -> log.error("Branch info invalid : {}", validateBranchInfo));
        }, () -> log.error("Shop info invalid : {}", validateBranchInfo));
        return isValid.get();
    }
}
