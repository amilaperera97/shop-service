package com.techbooker.shop.data;

import com.techbooker.shop.dto.BranchDataDto;
import com.techbooker.shop.dto.ContactInfoDto;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static feign.form.FormData.builder;

public class MockDataset {
    public static Map<ShopName, List<BranchDataDto>> BRANCH_INFO_BY_SHOP = new HashMap<>();

    MockDataset() {
        BRANCH_INFO_BY_SHOP.put(ShopName.ALDI, branchDataByShop(ShopName.ALDI));
        BRANCH_INFO_BY_SHOP.put(ShopName.LIDL, branchDataByShop(ShopName.LIDL));
        BRANCH_INFO_BY_SHOP.put(ShopName.TESCO, branchDataByShop(ShopName.TESCO));
        BRANCH_INFO_BY_SHOP.put(ShopName.POUNDLAND, branchDataByShop(ShopName.POUNDLAND));
    }

    private List<BranchDataDto> branchDataByShop(ShopName shopName) {
        List<BranchDataDto> branchData = new ArrayList<>();

        for (int index = 0; index < 10; index++) {
            switch (shopName) {
                case ALDI:
                    branchData.add(BranchDataDto.builder()
                            .name("ALDI" + index)
                            .contactInfo(ContactInfoDto.builder()
                                    .city("Cardiff").country("UK").postCode("CF239LZ")
                                    .build())
                            .build());
                    break;
            }

        }

        return branchData;
    }

}