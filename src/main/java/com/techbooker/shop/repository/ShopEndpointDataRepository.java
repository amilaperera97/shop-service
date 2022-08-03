package com.techbooker.shop.repository;

import com.techbooker.shop.model.EndpointData;
import com.techbooker.shop.model.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShopEndpointDataRepository extends JpaRepository<EndpointData, Long> {
    List<EndpointData> findByShopId(Long shopId);
}
