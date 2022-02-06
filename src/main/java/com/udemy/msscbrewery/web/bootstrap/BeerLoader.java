package com.udemy.msscbrewery.web.bootstrap;

import com.udemy.msscbrewery.web.domain.Beer;
import com.udemy.msscbrewery.web.model.BeerStyleEnum;
import com.udemy.msscbrewery.web.repository.BeerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;

import java.math.BigDecimal;

@RequiredArgsConstructor
public class BeerLoader implements CommandLineRunner {

    public static final long BEER_1_UPC = 063123420036L;
    public static final long BEER_2_UPC = 063123430001L;
    public static final long BEER_3_UPC = 18378337000L;

    private final BeerRepository beerRepository;


    @Override
    public void run(String... args) throws Exception {
        if (beerRepository.count() == 0) {
            loadBeerObjects();
        }

    }

    private void loadBeerObjects() {
        Beer b1 = Beer.builder()
                .beerName("Mango Bobs")
                .beerStyle(BeerStyleEnum.IPA)
                .quantityOnHand(12)
                .quantityToBrew(200)
                .price(new BigDecimal("12.95"))
                .upc(BEER_1_UPC)
                .build();

        Beer b2 = Beer.builder()
                .beerName("Galaxy Cat")
                .beerStyle(BeerStyleEnum.PALE_ALE)
                .quantityOnHand(12)
                .quantityToBrew(200)
                .price(new BigDecimal("12.95"))
                .upc(BEER_2_UPC)
                .build();

        Beer b3 = Beer.builder()
                .beerName("Pinball Porter")
                .beerStyle(BeerStyleEnum.PALE_ALE)
                .quantityOnHand(12)
                .quantityToBrew(200)
                .price(new BigDecimal("12.95"))
                .upc(BEER_3_UPC)
                .build();

        beerRepository.save(b1);
        beerRepository.save(b2);
        beerRepository.save(b3);
    }


}
