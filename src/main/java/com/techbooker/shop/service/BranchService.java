package com.techbooker.shop.service;

import com.techbooker.shop.dto.BranchDataDto;

import java.util.List;

public interface BranchService {

    BranchDataDto save(BranchDataDto data);
    BranchDataDto update(BranchDataDto data);
    void delete(BranchDataDto data);
    List<BranchDataDto> findAll();
    BranchDataDto findById(Long id);


}
