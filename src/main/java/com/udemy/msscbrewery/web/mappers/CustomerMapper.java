package com.udemy.msscbrewery.web.mappers;

import com.udemy.msscbrewery.web.domain.Customer;
import com.udemy.msscbrewery.web.model.CustomerDto;
import org.mapstruct.Mapper;

@Mapper
public interface CustomerMapper {

    Customer customerDtoToCustomer(Customer dto);

    CustomerDto customerToCustomerDto(Customer customer);

}
