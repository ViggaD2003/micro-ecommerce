package com.viggad.order.dto.request;

import com.viggad.order.dto.response.CustomerResponse;
import com.viggad.order.model.PaymentMethod;

import java.math.BigDecimal;

public record PaymentRequest(
        BigDecimal amount,
        PaymentMethod paymentMethod,
        Integer orderId,
        String orderReference,
        CustomerResponse customer
) {
}
