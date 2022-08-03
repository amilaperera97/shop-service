package com.techbooker.shop.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "data_map", schema = "shop_info", catalog = "scanmyway")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DataModel extends CommonModel {

    private String externalParamName;
    private String externalParamType;
    private String internalParamName;
    private String internalParamType;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shop_id")
    private Shop shop;
}
