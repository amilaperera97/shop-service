package com.techbooker.shop.dto;

import com.techbooker.shop.model.ParamType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExternalParamDataDto {
    private String paramName;
    private Long dataType;
    private ParamType paramType;
}