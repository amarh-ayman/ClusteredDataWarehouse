package com.progresssoft.deals.fx.repository;

import com.progresssoft.deals.fx.model.entites.FXDeal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FXDealRepo extends JpaRepository<FXDeal, String> {
    boolean existsByDealUniqueId(String dealUniqueId);
}
