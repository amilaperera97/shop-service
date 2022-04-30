package com.techbooker.shop.address;

import com.techbooker.shop.dto.address.UkPostalAddress;
import com.techbooker.shop.service.PostalAddressFinderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UkAddressFinderTests {
    @Autowired
    private PostalAddressFinderService postalAddressFinderService;

    @Test
    public void find() {
        UkPostalAddress ukPostalAddress = postalAddressFinderService.unitedKingdom("https://findaddress.io/","345","CF239LZ");
        System.out.println(ukPostalAddress);
    }
}
