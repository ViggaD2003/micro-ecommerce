package com.viggad.ecommerce.dto.response;

import com.viggad.ecommerce.model.Address;


public record CustomerResponse(
        String id,
        String firstName,
        String lastName,
        String email,
        Address address
) {

}
