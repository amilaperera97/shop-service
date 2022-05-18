package com.techbooker.shop.dto;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class ValidateBranchInfoDto {

    private int shopId;
    private int branchId;
    private String shopName;
    private String branchName;
    private ContactInfoDto branchContactInfo;
}
