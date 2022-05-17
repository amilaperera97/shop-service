package com.techbooker.shop.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "contact_info", schema = "shop_info")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ContactInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "post_code", nullable = false)
    private String postCode;

    @Column(name = "street_name", nullable = false)
    private String streetName;

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "country", nullable = false)
    private String country;

    @Column(name = "tel_mobile")
    private String telMobile;

    @Column(name = "tel_land")
    private String telLand;

    @Column(name = "email")
    private String email;

    public void setPostCode(String postCode) {
        this.postCode = postCode.replaceAll("\\s","");
    }
}