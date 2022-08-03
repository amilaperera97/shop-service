package com.techbooker.shop.dto;

import com.techbooker.shop.model.ContentType;
import com.techbooker.shop.model.ParamData;
import com.techbooker.shop.model.WebserviceType;
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
    private String featureName;
    private String endpoint;
    private ShopDataDto shop;
    private WebserviceType wsType;
    private ContentType requestBodyType;
    private String requestBody;
    private ContentType responseBodyType;
    private String responseBody;
    private HttpMethod httpMethod;
    private List<ParamData> paramData;
}
