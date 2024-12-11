package com.viggad.ecommerce.service.inte;

import java.util.List;
import com.viggad.ecommerce.dto.request.CustomerRequest;
import com.viggad.ecommerce.dto.response.CustomerResponse;

public interface CustomerService {
    String createCustomer(CustomerRequest request);

    void updateCustomer(CustomerRequest request);

    List<CustomerResponse> findAllCustomers();

    Boolean existsById(String id);

    CustomerResponse findById(String id);

    void deleteCustomer(String id);
}
