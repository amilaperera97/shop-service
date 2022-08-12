package com.techbooker.shop.repository;

import com.techbooker.shop.model.InternalParamModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InternalParamRepository extends JpaRepository<InternalParamModel, Long> {
    Optional<InternalParamModel> findByParamName(String name);
}
