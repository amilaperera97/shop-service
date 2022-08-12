package com.techbooker.shop.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EndPointParamDto {
    private String internalParamName;
    private ExternalParamDataDto externalParamData;
}
