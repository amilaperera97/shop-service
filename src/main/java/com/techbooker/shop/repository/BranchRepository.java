package com.techbooker.shop.repository;

import com.techbooker.shop.model.Branch;
import com.techbooker.shop.model.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BranchRepository extends JpaRepository<Branch, Long> {

    Optional<Branch> findBranchByIdAndShopAndNameAndContactInfoCityAndContactInfoPostCodeAndIsDeletedFalse(Long id, Shop shop, String name, String city, String postcode);
}
