package com.udemy.msscbrewery.web.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;

@JsonTest
public class BeerDtoSnakeTest extends BaseTest{

    @Autowired
    ObjectMapper objectMapper;


    @Test
    void testSnake() throws JsonProcessingException {
        BeerDto beerDto = getDto();
        String json = objectMapper.writeValueAsString(beerDto);
        System.out.println(json);
    }





}
