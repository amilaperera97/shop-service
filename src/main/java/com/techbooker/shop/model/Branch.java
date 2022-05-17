package com.techbooker.shop.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "branch", schema = "shop_info")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Branch extends CommonModel {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shop_id")
    private Shop shop;

    @Column(name = "name")
    private String name;

    @Column(name = "qr_code", nullable = false)
    private String qrCode;

    @OneToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "contact_info_id", nullable = false)
    private ContactInfo contactInfo;

}