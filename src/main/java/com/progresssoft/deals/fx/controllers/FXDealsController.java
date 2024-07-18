package com.progresssoft.deals.fx.controllers;

import com.progresssoft.deals.fx.model.dtos.FXDealDto;
import com.progresssoft.deals.fx.model.entites.FXDeal;
import com.progresssoft.deals.fx.services.FXDealService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Map;

@AllArgsConstructor
@Validated
@Slf4j
@RestController
@RequestMapping("/api/deals")
public class FXDealsController {
    private final FXDealService fxDealService;
    private static final Logger logger = LoggerFactory.getLogger(FXDealsController.class);

    @PostMapping
    public ResponseEntity<?> addData(@RequestBody @Valid FXDealDto fxDealDto) {
        return fxDealService.create(fxDealDto);
    }


    @GetMapping("/data")
    public List<FXDeal> getAllData() {
        logger.info("Received request to fetch all FXDeals");
        List<FXDeal> response = fxDealService.getAllData();
        logger.info("Fetched all FXDeals successfully");
        return response;
    }
}
