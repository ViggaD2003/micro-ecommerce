package com.viggad.ecommerce.service.impl;

import com.viggad.ecommerce.dto.request.ProductPurchaseRequest;
import com.viggad.ecommerce.dto.request.ProductRequest;
import com.viggad.ecommerce.dto.response.ProductResponse;
import com.viggad.ecommerce.dto.response.ProductPurchaseResponse;
import com.viggad.ecommerce.exception.ProductPurchaseException;
import com.viggad.ecommerce.mapper.ProductMapper;
import com.viggad.ecommerce.model.Product;
import com.viggad.ecommerce.repository.ProductRepository;
import com.viggad.ecommerce.service.inte.ProductService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    private final ProductMapper productMapper;


    @Override
    public Integer createProduct(ProductRequest request) {
        Product product = productMapper.toProduct(request);
        return productRepository.save(product).getId();
    }

    @Override
    public ProductResponse findById(Integer id) {
        return productRepository.findById(id)
                .map(productMapper::toProductResponse)
                .orElseThrow(() -> new EntityNotFoundException("Product not found"));
    }

    @Override
    public List<ProductResponse> findAll() {
        return productRepository.findAll().stream().map(productMapper::toProductResponse).collect(Collectors.toList());
    }

    @Override
    public List<ProductPurchaseResponse> purchaseProducts(List<ProductPurchaseRequest> requestList) {
        var productIds = requestList
                .stream()
                .map(ProductPurchaseRequest::productId)
                .toList();

        var storeProducts = productRepository.findAllByIdInOrderById(productIds);

        if(productIds.size() != storeProducts.size()){
            throw new ProductPurchaseException("One or more products does not exits");
        }

        var storeRequest = requestList.stream().sorted(Comparator.comparing(ProductPurchaseRequest::productId)).toList();

        var purchaseProducts = new ArrayList<ProductPurchaseResponse>();

        for (int i = 0; i < storeProducts.size(); i++) {
            var product = storeProducts.get(i);
            var productRequest = storeRequest.get(i);

            if(product.getAvailableQuantity() < productRequest.quantity()){
                throw new ProductPurchaseException("Insufficient stock quantity for product with ID ::" + product.getId());
            }
            var newAvailableQuantity = product.getAvailableQuantity() - productRequest.quantity();
            product.setAvailableQuantity(newAvailableQuantity);
            productRepository.save(product);
            purchaseProducts.add(productMapper.toproductPurchaseResponse(product, productRequest.quantity()));
        }

        return purchaseProducts;
    }
}
