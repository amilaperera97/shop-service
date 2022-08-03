package com.techbooker.shop.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "param_data", schema = "shop_info", catalog = "scanmyway")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ParamData extends CommonModel {
    @Column(name = "name")
    private String name;
    @Column(name = "value")
    private String value;
    @Enumerated(value = EnumType.STRING)
    @Column(name = "type")
    private ParamType paramType;
    @ManyToOne(targetEntity = EndpointData.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "endpoint_id")
    @JsonBackReference
    private EndpointData endpointData;
}
