package com.techbooker.shop.dto;

import com.techbooker.shop.model.*;
import com.techbooker.sm.util.dto.CommonDataDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpMethod;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EndpointDataDto extends CommonDataDto {
    private String host;
    private String endpointUrl;
    private ShopDataDto shop;
    private Long shopId;
    private WebserviceType wsType;
    private ContentType requestBodyType;
    private String requestBody;
    private ContentType responseBodyType;
    private String responseBody;
    private HttpMethod httpMethod;
    private Operations operation;
    private List<EndPointParamDto> endPointParams;
}