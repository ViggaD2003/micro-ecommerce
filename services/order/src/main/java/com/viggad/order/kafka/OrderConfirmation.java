package com.viggad.order.kafka;

import com.viggad.order.dto.response.CustomerResponse;
import com.viggad.order.dto.response.PurchaseResponse;
import com.viggad.order.model.PaymentMethod;
import java.util.List;
import java.math.BigDecimal;

public record OrderConfirmation(
        String orderReference,
        BigDecimal totalAmount,
        PaymentMethod paymentMethod,
        CustomerResponse customer,
        List<PurchaseResponse> products
) {
}
