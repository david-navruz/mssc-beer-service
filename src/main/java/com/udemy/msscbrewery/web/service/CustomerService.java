package com.udemy.msscbrewery.web.service;

import com.udemy.msscbrewery.web.model.CustomerDto;

import java.util.UUID;

public interface CustomerService {

    public CustomerDto getCustomerById(UUID id);

    CustomerDto saveNewCustomer(CustomerDto customerDto);

    void updateCustomer(UUID customerId, CustomerDto customerDto);

    void deleteById(UUID customerId);
}
