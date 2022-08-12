package com.techbooker.shop.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "data_types")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ParamDataType extends CommonModel {
    String name;
}
