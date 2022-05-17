package com.techbooker.shop.validation;

import com.techbooker.shop.dto.ContactInfoDto;
import com.techbooker.shop.dto.ValidateBranchInfoDto;
import com.techbooker.shop.model.Branch;
import com.techbooker.shop.model.ContactInfo;
import com.techbooker.shop.model.Shop;
import com.techbooker.shop.repository.BranchRepository;
import com.techbooker.shop.repository.ShopRepository;
import com.techbooker.shop.service.ValidationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class ValidationInfoTest {

    @Autowired
    private ValidationService validationService;

    @MockBean
    private BranchRepository branchRepository;
    @MockBean
    private ShopRepository shopRepository;


    @Test
    void branchInfoValidation() {
        Shop shop = shopRepository.save(shopData());

        Branch branch = Branch.builder()
                .shop(shop)
                .name("ALDI - Cardiff")
                .contactInfo(ContactInfo.builder()
                        .city("Cardiff")
                        .country("UK")
                        .postCode("CF239LZ")
                        .build())
                .build();
        branchRepository.save(branch);

        ValidateBranchInfoDto validateBranchInfo = ValidateBranchInfoDto.builder()
                .branchId(Integer.parseInt(String.valueOf(branch.getId())))
                .branchName(branch.getName())
                .shopId(Integer.parseInt(String.valueOf(shop.getId())))
                .branchContactInfo(ContactInfoDto.builder()
                        .postCode("CF239LZ")
                        .build())
                .build();
        Boolean result = validationService.isValid(validateBranchInfo);

        assertThat(result).isTrue();
    }

    private Shop shopData() {
        return Shop.builder().build();
    }
}
