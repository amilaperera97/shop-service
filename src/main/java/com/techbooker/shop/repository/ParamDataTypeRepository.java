package com.techbooker.shop.repository;

import com.techbooker.shop.model.ParamDataType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParamDataTypeRepository extends JpaRepository<ParamDataType, Long> {

}
