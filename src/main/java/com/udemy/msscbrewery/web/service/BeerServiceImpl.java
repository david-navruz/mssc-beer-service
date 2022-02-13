package com.udemy.msscbrewery.web.service;

import com.udemy.msscbrewery.web.controller.NotFoundException;
import com.udemy.msscbrewery.web.domain.Beer;
import com.udemy.msscbrewery.web.mappers.BeerMapper;
import com.udemy.msscbrewery.web.model.BeerDto;
import com.udemy.msscbrewery.web.model.BeerPagedList;
import com.udemy.msscbrewery.web.model.BeerStyleEnum;
import com.udemy.msscbrewery.web.repository.BeerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service("beerService")
public class BeerServiceImpl implements BeerService {

    private final BeerRepository beerRepository;
    private final BeerMapper beerMapper;


    @Cacheable(cacheNames = "beerListCache", condition = "#showInventoryOnHand == false ")
    @Override
    public BeerPagedList listBeers(String beerName, BeerStyleEnum beerStyle,
                                   PageRequest pageRequest, Boolean showInventoryOnHand) {
        BeerPagedList beerPagedList = null;
        Page<Beer> beerPage;

        if(!StringUtils.isEmpty(beerName) && !StringUtils.isEmpty(beerStyle)){
            // search both
            beerPage = beerRepository.findAllByBeerNameAndBeerStyle(beerName, beerStyle, pageRequest);
        }
        else if (!StringUtils.isEmpty(beerName) && StringUtils.isEmpty(beerStyle)){
            // search beer_service name
            beerPage = beerRepository.findAllByBeerName(beerName, pageRequest);
        }
        else if (StringUtils.isEmpty(beerName) && !StringUtils.isEmpty(beerStyle)){
            // search beer_service style
            beerPage = beerRepository.findAllByBeerStyle(beerStyle, pageRequest);
        }
        else {
            beerPage = beerRepository.findAll(pageRequest);
        }

        if (showInventoryOnHand) {
            beerPagedList = new BeerPagedList(beerPage
                    .getContent()
                    .stream()
                    .map(beerMapper::beerToBeerDto)
                    .collect(Collectors.toList()),
                    PageRequest
                            .of(beerPage.getPageable().getPageNumber(),
                                    beerPage.getPageable().getPageSize()),
                    beerPage.getTotalElements());
        }
        return beerPagedList;
    }


    @Cacheable(cacheNames = "beerCache", key = "#beerId", condition = "#showInventoryOnHand == false ")
    @Override
    public BeerDto getById(UUID beerId, Boolean showInventoryOnHand) {
        // fetching the Beer entity by id and converting into BeerDto
        return beerMapper.beerToBeerDto(beerRepository.findById(beerId).orElseThrow(NotFoundException::new));
    }


    @Override
    public BeerDto saveNewBeer(BeerDto beerDto) {
        // converting the BeerDto into Beer
        Beer beerToSave = beerMapper.beerDtoToBeer(beerDto);
        // saving the Beer object
        return beerMapper.beerToBeerDto(beerRepository.save(beerToSave));
    }


    @Override
    public BeerDto updateBeer(UUID beerId, BeerDto beerDto) {
        Beer beer = beerRepository.findById(beerId).orElseThrow(NotFoundException::new);
        beer.setBeerName(beerDto.getBeerName());
        beer.setBeerStyle(beerDto.getBeerStyle());
        beer.setPrice(beerDto.getPrice());
        beer.setUpc(beerDto.getUpc());
        return beerMapper.beerToBeerDto(beerRepository.save(beer));
    }


    @Cacheable(cacheNames = "beerUpcCache")
    @Override
    public BeerDto getByUpc(String upc) {
        return beerMapper.beerToBeerDto(beerRepository.findByUpc(upc));
    }

}
