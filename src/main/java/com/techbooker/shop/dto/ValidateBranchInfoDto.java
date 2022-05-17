package com.techbooker.shop.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@NotNull
@NotEmpty
@Builder
public class ValidateBranchInfoDto {

    private int shopId;
    private int branchId;
    private String shopName;
    private String branchName;
    private ContactInfoDto branchContactInfo;
}
