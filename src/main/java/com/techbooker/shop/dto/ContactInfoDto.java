package com.techbooker.shop.dto;

import lombok.Data;

@Data
public class ContactInfoDto {
    private String streetName;
    private String city;
    private String country;
    private String postCode;
    private String telMobile;
    private String telLand;
    private String email;
}
