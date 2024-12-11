package com.viggad.ecommerce.mapper;

import com.viggad.ecommerce.dto.request.CustomerRequest;
import com.viggad.ecommerce.dto.response.CustomerResponse;
import com.viggad.ecommerce.model.Customer;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class CustomerMapper {

    public Customer toCustomer(CustomerRequest request){
        if(request == null) return null;

        return Customer.builder()
                .id(request.id())
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .address(request.address())
                .build();
//        return new Customer(request.id(), request.firstName(), request.lastName(), request.email(),request.address());
    }

    public CustomerResponse fromCustomer(Customer customer){
        return new CustomerResponse(
                customer.getId(),
                customer.getFirstName(),
                customer.getEmail(),
                customer.getLastName(),
                customer.getAddress()
        );
    }
}