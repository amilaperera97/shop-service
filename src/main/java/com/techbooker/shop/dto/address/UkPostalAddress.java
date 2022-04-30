package com.techbooker.shop.dto.address;

import lombok.Data;

@Data
public class UkPostalAddress {
    private String result;
    private float callsRemaining;
    private float creditsRemaining;
    private String latitude;
    private String longitude;
    ExpandedAddress ExpandedAddressObject;
    private String csvAddress;
    private String statusCode;
}
