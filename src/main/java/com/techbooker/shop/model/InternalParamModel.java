package com.techbooker.shop.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "internal_param_model")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InternalParamModel extends CommonModel {
    private String paramName;
    @OneToOne
    private ParamDataType dataType;
    /* if enum */
    private String value;
}
