package com.viggad.ecommerce.service.iml;

import com.viggad.ecommerce.dto.request.CustomerRequest;
import com.viggad.ecommerce.dto.response.CustomerResponse;
import com.viggad.ecommerce.exception.CustomerNotFoundException;
import com.viggad.ecommerce.mapper.CustomerMapper;
import com.viggad.ecommerce.model.Customer;
import com.viggad.ecommerce.repository.CustomerRepository;
import com.viggad.ecommerce.service.inte.CustomerService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository repository;

    private final CustomerMapper mapper;

    @Override
    public String createCustomer(CustomerRequest request) {
        var customer = repository.save(mapper.toCustomer(request));
        return customer.getId();
    }

    @Override
    public void updateCustomer(CustomerRequest request) {
        var customer = repository.findById(request.id())
                .orElseThrow(() -> new CustomerNotFoundException(
                        String.format("Cannot update customer:: No customer found with the provided ID:: %s", request.id())
                ));
        mergerCustomer(customer, request);
        repository.save(customer);
    }

    @Override
    public List<CustomerResponse> findAllCustomers() {
        return repository.findAll().stream().map(mapper::fromCustomer).toList();
    }

    @Override
    public Boolean existsById(String id) {
        return repository.findById(id).isPresent();
    }

    @Override
    public CustomerResponse findById(String id) {
        return repository.findById(id)
                .map(mapper::fromCustomer)
                .orElseThrow(() -> new CustomerNotFoundException(String.format("No customer found with the provided ID: %s", id)));
    }

    @Override
    public void deleteCustomer(String id) {
        if(id != null){
            repository.deleteById(id);
        } else {
            throw new CustomerNotFoundException(String.format("No customer found with the provided ID: %s", id));
        }
    }

    private void mergerCustomer(Customer customer, CustomerRequest request) {
        if(StringUtils.isNotBlank(request.firstName())){
            customer.setFirstName(request.firstName());
        }

        if(StringUtils.isNotBlank(request.lastName())){
            customer.setFirstName(request.lastName());
        }

        if(StringUtils.isNotBlank(request.email())){
            customer.setFirstName(request.email());
        }

        if(request.address() != null){
            customer.setAddress(request.address());
        }
    }
}
