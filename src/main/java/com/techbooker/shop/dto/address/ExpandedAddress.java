package com.techbooker.shop.dto.address;

import lombok.Data;

@Data
public class ExpandedAddress {
    private String house;
    private String street;
    private String locality;
    private String town;
    private String district;
    private String county;
    private String pCode;
}
