package com.techbooker.shop.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "shop", schema = "shop_info")
public class Shop extends CommonModel {

    @Column(name = "name", nullable = false)
    private String name;

    @OneToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "contact_info_id", nullable = false)
    private ContactInfo contactInfo;

}