package com.techbooker.shop.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContactInfoDto {
    private String streetName;
    private String city;
    private String country;
    private String postCode;
    private String telMobile;
    private String telLand;
    private String email;
}
