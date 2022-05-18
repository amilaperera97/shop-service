package com.techbooker.shop.service.impl;

import com.techbooker.shop.dto.BranchDataDto;
import com.techbooker.shop.dto.QrCodeType;
import com.techbooker.shop.exception.custom.ResourceNotFoundException;
import com.techbooker.shop.model.Branch;
import com.techbooker.shop.repository.BranchRepository;
import com.techbooker.shop.repository.ShopRepository;
import com.techbooker.shop.service.BranchService;
import com.techbooker.shop.util.CommonUtil;
import com.techbooker.shop.util.converter.EntityConverter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

import java.util.stream.Collectors;

import static com.techbooker.shop.util.contance.ParamConstance.*;

@RequiredArgsConstructor
@Slf4j
@Service
public class BranchServiceImpl implements BranchService {
    private final BranchRepository branchRepository;
    private final ShopRepository shopRepository;
    private final EntityConverter entityConverter;
    private final CommonUtil commonUtil;
    private final MessageSource messageSource;

    @Override
    public BranchDataDto save(BranchDataDto data) {
        Branch branch = saveBranchData(data, false);
        data.setId(branch.getId());

        BranchDataDto branchDataDto = entityConverter.convert(branch, BranchDataDto.class);

        setUserInfo(data, branch, branchDataDto);
        log.info("Save branch info : {}", branchDataDto);
        return branchDataDto;
    }

    @Override
    public BranchDataDto update(BranchDataDto data) {
        Branch branch = saveBranchData(data, true);

        BranchDataDto branchDataDto = entityConverter.convert(branch, BranchDataDto.class);
        setUserInfo(data, branch, branchDataDto);

        return branchDataDto;
    }

    @Override
    public void delete(BranchDataDto data) {
        branchRepository.findById(data.getId()).ifPresent(this::delete);
    }

    @Override
    public List<BranchDataDto> findAll() {
        List<Branch> branchList = branchRepository.findAll().stream()
                .filter(branch -> !branch.getIsDeleted()).collect(Collectors.toList());
        return entityConverter.convert(branchList, BranchDataDto.class);
    }

    @Override
    public BranchDataDto findById(Long id) {
        Optional<Branch> branch = branchRepository.findById(id).filter(branchInfo -> !branchInfo.getIsDeleted());

        if (branch.isPresent()) {
            return entityConverter.convert(branch.get(), BranchDataDto.class);
        }
        throw new ResourceNotFoundException(HttpStatus.NOT_FOUND,
                messageSource.getMessage("no.records.matches", new Object[0], Locale.ENGLISH));
    }

    private Map<String, String> branchData(BranchDataDto data) {
        Map<String, String> branch = new HashMap<>();
        branch.put(BRANCH_ID, String.valueOf(data.getId()));
        branch.put(BRANCH_NAME, data.getName());
        branch.put(SHOP_ID, String.valueOf(data.getShopId()));
        branch.put(BRANCH_POSTCODE, data.getContactInfo().getPostCode());
        branch.put(BRANCH_CITY, data.getContactInfo().getCity());
        branch.put(CREATED_DATE, LocalDateTime.now().toString());
        return branch;
    }

    private Branch saveBranchData(BranchDataDto data, Boolean isUpdate) {
        Branch branch = entityConverter.convert(data, Branch.class);
        if (Objects.equals(Boolean.TRUE, isUpdate)) {
            branch.setLastUpdatedUser(data.getUser().getUsername());
        } else {
            branch.setCreatedUser(data.getUser().getUsername());
        }
        branch.setQrCode("empty");

        shopRepository.findById(data.getShopId())
                .ifPresentOrElse(branch::setShop,
                        () -> {
                            throw new ResourceNotFoundException(HttpStatus.NOT_FOUND,
                                    messageSource.getMessage("no.records.matches", new Object[0], Locale.ENGLISH));
                        });

        //Persist data without updated QR
        Branch persistedRecord = branchRepository.save(branch);
        data.setId(persistedRecord.getId());
        data.setShopName(persistedRecord.getShop().getName());

        String filePath = commonUtil.generateQrCode(QrCodeType.BRANCH, branchData(data));
        branch.setQrCode(filePath);
        log.info("QR file path : {} | Branch Id : {}", filePath, branch.getId());
        return branchRepository.save(branch);
    }

    private void delete(Branch branch) {
        branch.setIsDeleted(true);
        branchRepository.saveAndFlush(branch);
    }

    private void setUserInfo(BranchDataDto data, Branch branch, BranchDataDto branchDataDto) {
        if (branchDataDto != null) {
            branchDataDto.setShopId(branch.getShop().getId());
            branchDataDto.setUser(data.getUser());
        }
    }
}
