package com.progresssoft.deals.fx.controllers;

import com.progresssoft.deals.fx.model.dtos.FXDealDto;
import com.progresssoft.deals.fx.model.entites.FXDeal;
import com.progresssoft.deals.fx.services.FXDealService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.*;

@WebMvcTest(FXDealsController.class)
@ExtendWith(MockitoExtension.class)
public class FXDealsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FXDealService fxDealService;

    private FXDealDto fxDealDto;
    private FXDeal fxDeal;

    @BeforeEach
    void setUp() {
        fxDealDto = new FXDealDto(1L, "deal123", "USD", "EUR", 1000.0, LocalDateTime.now());
        fxDeal = new FXDeal(1L, "deal123", "USD", "EUR", 1000.0, LocalDateTime.now());
    }

    @Test
    void testAddData() throws Exception {
        Map<String, String> response = Map.of(
                "dealUniqueId", "deal123",
                "message", "success"
        );
        when(fxDealService.create(any(FXDealDto.class)))
                .thenReturn(ResponseEntity.status(HttpStatus.CREATED).body(response));

        mockMvc.perform(post("/api/deals")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":1,\"dealUniqueId\":\"deal123\",\"fromCurrencyIsoCode\":\"USD\",\"toCurrencyIsoCode\":\"EUR\",\"dealAmount\":1000.0,\"dealTimestamp\":\"2024-07-17T21:23:09.614165\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.dealUniqueId", is("deal123")))
                .andExpect(jsonPath("$.message", is("success")));
    }

    @Test
    void testGetAllData() throws Exception {
        List<FXDeal> fxDealList = Arrays.asList(fxDeal, new FXDeal(2L, "deal124", "USD", "GBP", 2000.0, LocalDateTime.now()));

        when(fxDealService.getAllData()).thenReturn(fxDealList);

        mockMvc.perform(get("/api/deals/data")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].dealUniqueId", is("deal123")))
                .andExpect(jsonPath("$[1].dealUniqueId", is("deal124")));
    }
}
