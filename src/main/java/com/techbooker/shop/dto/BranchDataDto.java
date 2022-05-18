package com.techbooker.shop.dto;

import com.techbooker.sm.util.dto.CommonDataDto;
import lombok.*;
import org.jetbrains.annotations.NotNull;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@Builder
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class BranchDataDto extends CommonDataDto {
    private Long id;
    @NotNull
    @NotEmpty
    private Long shopId;
    private String shopName;
    @NotNull
    @NotEmpty
    private String name;
    private ContactInfoDto contactInfo;
}