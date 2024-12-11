package com.viggad.ecommerce.service.inte;

import com.viggad.ecommerce.dto.request.ProductPurchaseRequest;
import com.viggad.ecommerce.dto.request.ProductRequest;
import com.viggad.ecommerce.dto.response.ProductResponse;
import com.viggad.ecommerce.dto.response.ProductPurchaseResponse;

import java.util.List;

public interface ProductService {

    Integer createProduct(ProductRequest request);

    ProductResponse findById(Integer id);

    List<ProductResponse> findAll();
    List<ProductPurchaseResponse> purchaseProducts(List<ProductPurchaseRequest> requestList);
}
