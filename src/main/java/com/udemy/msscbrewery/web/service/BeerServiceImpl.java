package com.udemy.msscbrewery.web.service;


import com.udemy.msscbrewery.web.model.BeerDto;
import com.udemy.msscbrewery.web.model.BeerStyleEnum;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class BeerServiceImpl implements BeerService {


    @Override
    public BeerDto getBeerById(UUID beerId) {
        return BeerDto.builder().id(UUID.randomUUID())
                .beerName("Heineken")
                .beerStyle(BeerStyleEnum.PALE_ALE)
                .build();
    }

    @Override
    public BeerDto saveNewBeer(BeerDto beerDto) {
        return BeerDto.builder()
                .id(UUID.randomUUID())
                .build();
    }

    @Override
    public void updateBeer(UUID beerId, BeerDto beerDto) {

    }



}
