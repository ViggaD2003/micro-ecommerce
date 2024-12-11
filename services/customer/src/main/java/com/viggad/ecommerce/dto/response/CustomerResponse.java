package com.viggad.ecommerce.dto.response;

import com.viggad.ecommerce.model.Address;


public record CustomerResponse(
        String id,
        String firstname,
        String lastname,
        String email,
        Address address
) {

}
