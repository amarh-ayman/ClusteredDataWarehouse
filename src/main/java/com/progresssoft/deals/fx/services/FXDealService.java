package com.progresssoft.deals.fx.services;

import com.progresssoft.deals.fx.model.dtos.FXDealDto;
import com.progresssoft.deals.fx.model.entites.FXDeal;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface FXDealService {
    ResponseEntity<Map<String, String>> create(final FXDealDto fxdeal);
    List<FXDeal> getAllData();
}
