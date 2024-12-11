package com.viggad.ecommerce.mapper;

import com.viggad.ecommerce.dto.request.ProductRequest;
import com.viggad.ecommerce.dto.response.ProductPurchaseResponse;
import com.viggad.ecommerce.dto.response.ProductResponse;
import com.viggad.ecommerce.model.Category;
import com.viggad.ecommerce.model.Product;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Service;

@Service
public class ProductMapper {

    public Product toProduct(ProductRequest request) {
        return Product.builder()
                .name(request.name())
                .description(request.description())
                .price(request.price())
                .availableQuantity(request.availableQuantity())
                .category(Category.builder()
                        .id(request.categoryId())
                        .build())
                .build();
    }

    public ProductResponse toProductResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .availableQuantity(product.getAvailableQuantity())
                .categoryId(product.getCategory().getId())
                .categoryName(product.getCategory().getName())
                .categoryDescription(product.getCategory().getDescription())
                .build();
    }

    public ProductPurchaseResponse toProductPurchaseResponse(Product product, @NotNull(message = "Quantity is mandatory") Double quantity) {
return ProductPurchaseResponse.builder()
        .productId(product.getId())
        .name(product.getName())
        .description(product.getDescription())
        .price(product.getPrice())
        .quantity(quantity)
        .build();
    }
}
