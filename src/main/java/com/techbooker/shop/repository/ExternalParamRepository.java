package com.techbooker.shop.repository;

import com.techbooker.shop.model.ExternalParamModel;
import com.techbooker.shop.model.InternalParamModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ExternalParamRepository extends JpaRepository<ExternalParamModel, Long> {
    Optional<InternalParamModel> findByParamName(String name);
}
