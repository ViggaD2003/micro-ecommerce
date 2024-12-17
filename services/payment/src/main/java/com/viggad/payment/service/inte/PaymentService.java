package com.viggad.payment.service.inte;

import com.viggad.payment.dto.request.PaymentRequest;

public interface PaymentService {

    Integer createPayment(PaymentRequest request);

}
