package com.techbooker.shop.service;

import com.techbooker.shop.dto.ValidateBranchInfoDto;

public interface ValidationService {
    Boolean isValid(ValidateBranchInfoDto validateBranchInfo);
}
