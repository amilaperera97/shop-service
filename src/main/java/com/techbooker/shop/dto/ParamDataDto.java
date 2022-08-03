package com.techbooker.shop.dto;


import com.techbooker.shop.model.ParamType;
import lombok.Data;

@Data
public class ParamDataDto {
    private String name;
    private String value;
    private ParamType paramType;
}
