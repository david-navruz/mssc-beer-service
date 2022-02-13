package com.udemy.msscbrewery.web.model;

import com.udemy.msscbrewery.web.bootstrap.BeerLoader;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

public class BaseTest {

    BeerDto getDto() {
        return BeerDto.builder()
                .beerName("Heineken")
                .beerStyle(BeerStyleEnum.LAGER)
                .id(UUID.randomUUID())
                .createdDate(OffsetDateTime.now())
                .lastModifiedDate(OffsetDateTime.now())
                .price(new BigDecimal("12.99"))
                .upc(BeerLoader.BEER_2_UPC)
                .build();
    }


}
