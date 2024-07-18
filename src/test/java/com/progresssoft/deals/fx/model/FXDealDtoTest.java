package com.progresssoft.deals.fx.model;

import com.progresssoft.deals.fx.model.dtos.FXDealDto;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class FXDealDtoTest {

    @Test
    public void testFXDealDtoValid() {
        FXDealDto fxDealDto = new FXDealDto(1L, "deal123", "USD", "EUR", 1000.0, LocalDateTime.now());

        assertNotNull(fxDealDto.getId());
        assertNotNull(fxDealDto.getDealUniqueId());
        assertFalse(fxDealDto.getDealUniqueId().isEmpty());
        assertNotNull(fxDealDto.getFromCurrencyIsoCode());
        assertFalse(fxDealDto.getFromCurrencyIsoCode().isEmpty());
        assertEquals(3, fxDealDto.getFromCurrencyIsoCode().length());
        assertNotNull(fxDealDto.getToCurrencyIsoCode());
        assertFalse(fxDealDto.getToCurrencyIsoCode().isEmpty());
        assertEquals(3, fxDealDto.getToCurrencyIsoCode().length());
        assertNotNull(fxDealDto.getDealAmount());
        assertTrue(fxDealDto.getDealAmount() > 0);
        assertNotNull(fxDealDto.getDealTimestamp());
    }

    @Test
    public void testFXDealDtoNullFields() {
        FXDealDto fxDealDto = new FXDealDto(null, null, null, null, null, null);

        assertNull(fxDealDto.getId());
        assertNull(fxDealDto.getDealUniqueId());
        assertNull(fxDealDto.getFromCurrencyIsoCode());
        assertNull(fxDealDto.getToCurrencyIsoCode());
        assertNull(fxDealDto.getDealAmount());
        assertNull(fxDealDto.getDealTimestamp());
    }

    @Test
    public void testFXDealDtoEmptyFields() {
        FXDealDto fxDealDto = new FXDealDto(1L, "", "", "", 0.0, LocalDateTime.now());

        assertNotNull(fxDealDto.getId());
        assertNotNull(fxDealDto.getDealUniqueId());
        assertTrue(fxDealDto.getDealUniqueId().isEmpty());
        assertNotNull(fxDealDto.getFromCurrencyIsoCode());
        assertTrue(fxDealDto.getFromCurrencyIsoCode().isEmpty());
        assertNotEquals(3, fxDealDto.getFromCurrencyIsoCode().length());
        assertNotNull(fxDealDto.getToCurrencyIsoCode());
        assertTrue(fxDealDto.getToCurrencyIsoCode().isEmpty());
        assertNotEquals(3, fxDealDto.getToCurrencyIsoCode().length());
        assertNotNull(fxDealDto.getDealAmount());
        assertEquals(0.0, fxDealDto.getDealAmount());
        assertNotNull(fxDealDto.getDealTimestamp());
    }

    @Test
    public void testFXDealDtoInvalidCurrencyCodeLength() {
        FXDealDto fxDealDto = new FXDealDto(1L, "deal123", "US", "EU", 1000.0, LocalDateTime.now());

        assertNotNull(fxDealDto.getId());
        assertNotNull(fxDealDto.getDealUniqueId());
        assertNotNull(fxDealDto.getFromCurrencyIsoCode());
        assertEquals(2, fxDealDto.getFromCurrencyIsoCode().length());
        assertNotNull(fxDealDto.getToCurrencyIsoCode());
        assertEquals(2, fxDealDto.getToCurrencyIsoCode().length());
        assertNotNull(fxDealDto.getDealAmount());
        assertTrue(fxDealDto.getDealAmount() > 0);
        assertNotNull(fxDealDto.getDealTimestamp());
    }

    @Test
    public void testFXDealDtoInvalidDealAmount() {
        FXDealDto fxDealDto = new FXDealDto(1L, "deal123", "USD", "EUR", -1.0, LocalDateTime.now());

        assertNotNull(fxDealDto.getId());
        assertNotNull(fxDealDto.getDealUniqueId());
        assertNotNull(fxDealDto.getFromCurrencyIsoCode());
        assertEquals(3, fxDealDto.getFromCurrencyIsoCode().length());
        assertNotNull(fxDealDto.getToCurrencyIsoCode());
        assertEquals(3, fxDealDto.getToCurrencyIsoCode().length());
        assertNotNull(fxDealDto.getDealAmount());
        assertTrue(fxDealDto.getDealAmount() <= 0);
        assertNotNull(fxDealDto.getDealTimestamp());
    }
}
