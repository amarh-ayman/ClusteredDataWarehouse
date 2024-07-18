package com.progresssoft.deals.fx.services;

import com.progresssoft.deals.fx.exceptions.RequestAlreadyDefinedException;
import com.progresssoft.deals.fx.model.dtos.FXDealDto;
import com.progresssoft.deals.fx.model.entites.FXDeal;
import com.progresssoft.deals.fx.repository.FXDealRepo;
import com.progresssoft.deals.fx.services.implementation.FXDealServiceImp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FXDealServiceImpTest {

    @InjectMocks
    private FXDealServiceImp fxDealService;

    @Mock
    private FXDealRepo fxDealRepo;

    @Mock
    private ModelMapper modelMapper;

    private FXDealDto fxDealDto;
    private FXDeal fxDeal;

    @BeforeEach
    void setUp() {
        fxDealDto = new FXDealDto(1L, "deal123", "USD", "EUR", 1000.0, LocalDateTime.now());
        fxDeal = new FXDeal(1L, "deal123", "USD", "EUR", 1000.0, LocalDateTime.now());
    }

    @Test
    void testCreateFXDealSuccess() {
        Map<String, String> response = Map.of(
                "dealUniqueId", "deal123",
                "message", "success"
        );

        when(fxDealRepo.existsByDealUniqueId(fxDealDto.getDealUniqueId())).thenReturn(false);
        when(modelMapper.map(fxDealDto, FXDeal.class)).thenReturn(fxDeal);
        when(fxDealRepo.save(fxDeal)).thenReturn(fxDeal);

        ResponseEntity<Map<String, String>> result = fxDealService.create(fxDealDto);

        assertNotNull(result);
        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        assertEquals(response, result.getBody());

        verify(fxDealRepo, times(1)).existsByDealUniqueId(fxDealDto.getDealUniqueId());
        verify(fxDealRepo, times(1)).save(fxDeal);
    }

    @Test
    void testCreateFXDealAlreadyExists() {
        when(fxDealRepo.existsByDealUniqueId(fxDealDto.getDealUniqueId())).thenReturn(true);

        assertThrows(RequestAlreadyDefinedException.class, () -> fxDealService.create(fxDealDto));

        verify(fxDealRepo, times(1)).existsByDealUniqueId(fxDealDto.getDealUniqueId());
        verify(fxDealRepo, never()).save(any(FXDeal.class));
    }

    @Test
    void testGetAllData() {
        FXDeal deal1 = new FXDeal(1L, "deal123", "USD", "EUR", 1000.0, LocalDateTime.now());
        FXDeal deal2 = new FXDeal(2L, "deal124", "USD", "GBP", 2000.0, LocalDateTime.now());

        when(fxDealRepo.findAll()).thenReturn(Arrays.asList(deal1, deal2));

        List<FXDeal> result = fxDealService.getAllData();

        assertEquals(2, result.size());
        assertNotNull(result.get(0));
        assertNotNull(result.get(1));
        assertEquals("deal123", result.get(0).getDealUniqueId());
        assertEquals("deal124", result.get(1).getDealUniqueId());

        verify(fxDealRepo, times(1)).findAll();
    }
}
