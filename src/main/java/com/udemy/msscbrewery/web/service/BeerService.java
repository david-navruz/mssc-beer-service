package com.udemy.msscbrewery.web.service;

import com.udemy.msscbrewery.web.model.BeerDto;
import java.util.UUID;

public interface BeerService {

    public BeerDto getBeerById(UUID beerId);

    public BeerDto saveNewBeer(BeerDto beerDto);

    public void updateBeer(UUID beerId, BeerDto beerDto);

}
