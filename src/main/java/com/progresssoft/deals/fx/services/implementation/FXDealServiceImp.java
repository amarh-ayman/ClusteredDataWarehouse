package com.progresssoft.deals.fx.services.implementation;

import com.progresssoft.deals.fx.exceptions.RequestAlreadyDefinedException;
import com.progresssoft.deals.fx.model.dtos.FXDealDto;
import com.progresssoft.deals.fx.model.entites.FXDeal;
import com.progresssoft.deals.fx.repository.FXDealRepo;
import com.progresssoft.deals.fx.services.FXDealService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import lombok.AllArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@Service
public class FXDealServiceImp implements FXDealService {
    private final FXDealRepo fxDealRepository;
    private final ModelMapper modelMapper;

    @Override
    public ResponseEntity<Map<String, String>> create(final FXDealDto fxDealDto) {
        if (fxDealRepository.existsByDealUniqueId(fxDealDto.getDealUniqueId())) {
            throw new RequestAlreadyDefinedException("Deal is already there.");
        }

        FXDeal fxDeal = modelMapper.map(fxDealDto, FXDeal.class);
        FXDeal savedModel = fxDealRepository.save(fxDeal);

        Map<String, String> response = new HashMap<>();
        response.put("dealUniqueId", savedModel.getDealUniqueId());
        response.put("message", "success");

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Override
    public List<FXDeal> getAllData() {
        return fxDealRepository.findAll();
    }


}
