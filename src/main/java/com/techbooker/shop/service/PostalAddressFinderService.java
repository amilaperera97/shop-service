package com.techbooker.shop.service;

import com.techbooker.shop.conf.FeignSupportConfig;
import com.techbooker.shop.dto.address.UkPostalAddress;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "addressFinderService",url = "${url.external.uk-address-finder}")
public interface PostalAddressFinderService {

    @PostMapping(value = "/{houseNo}/{postalCode}", produces = MediaType.APPLICATION_JSON_VALUE)
    UkPostalAddress unitedKingdom(@RequestHeader("referer") String referer,
                                  @PathVariable("houseNo") String houseNo,
                                  @PathVariable("postalCode") String postalCode);

}
