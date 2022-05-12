package com.techbooker.shop.conf;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix = "shop-service.properties")
@Configuration("shopServiceProperties")
@Getter
@Setter
@ToString
public class ShopServiceProperties {

    private String qrFilePath;
    private int qrHeight;
    private int qrWidth;
}
