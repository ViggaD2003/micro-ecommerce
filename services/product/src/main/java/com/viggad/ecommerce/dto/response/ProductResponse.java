package com.viggad.ecommerce.dto.response;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Builder
public record ProductResponse(
         Integer id,
         String name,
         String description,
         Double availableQuantity,
         BigDecimal price,
         Integer categoryId,
         String categoryName,
         String categoryDescription
) {
}
