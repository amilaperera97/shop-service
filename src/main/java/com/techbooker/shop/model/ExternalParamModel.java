package com.techbooker.shop.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "external_param_model")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExternalParamModel extends CommonModel {
    private String paramName;
    @OneToOne
    private ParamDataType dataType;
    /* if enum */
    private String value;
    @ManyToOne
    @JoinColumn(name = "endpoint_id")
    @JsonBackReference
    private EndpointData endpointData;
    @OneToOne
    private InternalParamModel internalParam;

    @Enumerated(EnumType.STRING)
    @Column(name = "param_type", nullable = false)
    private ParamType paramType;
}
