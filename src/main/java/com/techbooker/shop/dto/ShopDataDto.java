package com.techbooker.shop.dto;

import com.techbooker.sm.util.dto.CommonDataDto;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class ShopDataDto extends CommonDataDto {
    private Long id;
    private String name;
    private ContactInfoDto contactInfo;
}
