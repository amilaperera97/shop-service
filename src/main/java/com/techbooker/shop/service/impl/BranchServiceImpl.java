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

import java.util.*;

import java.util.stream.Collectors;

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
        Branch branch = saveBranchData(data);
        branch.setCreatedUser(data.getUser().getUsername());

        Branch savedBranch = branchRepository.save(branch);
        return entityConverter.convert(savedBranch, BranchDataDto.class);
    }

    @Override
    public BranchDataDto update(BranchDataDto data) {
        Branch savedBranch = branchRepository.save(saveBranchData(data));
        return entityConverter.convert(savedBranch, BranchDataDto.class);
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
        branch.put("name", data.getName());
        branch.put("shopId",String.valueOf(data.getShopId()));
        return branch;
    }
    private Branch saveBranchData(BranchDataDto data) {
        Branch branch = entityConverter.convert(data, Branch.class);
        branch.setLastUpdatedUser(data.getUser().getUsername());
        branch.setShop(shopRepository.getById(data.getShopId()));

        String filePath = commonUtil.generateQrCode(QrCodeType.BRANCH, branchData(data));
        branch.setQrCode(filePath);
        return branch;
    }

    private void delete(Branch branch) {
        branch.setIsDeleted(true);
        branchRepository.saveAndFlush(branch);
    }
}
