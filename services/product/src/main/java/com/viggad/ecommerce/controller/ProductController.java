package com.viggad.ecommerce.controller;

import com.viggad.ecommerce.dto.request.ProductPurchaseRequest;
import com.viggad.ecommerce.dto.request.ProductRequest;
import com.viggad.ecommerce.dto.response.ProductResponse;
import com.viggad.ecommerce.dto.response.ProductPurchaseResponse;
import com.viggad.ecommerce.service.inte.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/products")
//@RequiredArgsConstructor
public class ProductController {

    @Autowired
    private  ProductService productService;

    @PostMapping
    public ResponseEntity<Integer> createProduct(
            @RequestBody @Valid ProductRequest request
            ){
        return ResponseEntity.ok(productService.createProduct(request));
    }

    @PostMapping("/purchase")
    public ResponseEntity<List<ProductPurchaseResponse>> purchaseProducts(
            @RequestBody List<ProductPurchaseRequest> requestList
    ){
        return ResponseEntity.ok(productService.purchaseProducts(requestList));
    }


    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> findById(
            @PathVariable("id") Integer id
    ){
        return ResponseEntity.ok(productService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> findAll() {
        return ResponseEntity.ok(productService.findAll());
    }
}
