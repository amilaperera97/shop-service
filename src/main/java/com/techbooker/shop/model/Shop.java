package com.techbooker.shop.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "shop", schema = "shop_info")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Shop extends CommonModel {

    @Column(name = "name", nullable = false)
    private String name;

    @OneToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "contact_info_id", nullable = false)
    private ContactInfo contactInfo;

    @OneToMany(mappedBy = "id")
    private List<Branch> branches;

    @OneToMany(mappedBy = "id")
    private List<EndpointData> endpointData;
}