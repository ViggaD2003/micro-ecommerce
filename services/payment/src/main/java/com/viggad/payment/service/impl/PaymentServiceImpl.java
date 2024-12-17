package com.viggad.payment.service.impl;

import com.viggad.payment.PaymentRepository;
import com.viggad.payment.dto.request.PaymentRequest;
import com.viggad.payment.mapper.PaymentMapper;
import com.viggad.payment.notification.NotificationProducer;
import com.viggad.payment.notification.PaymentNotificationRequest;
import com.viggad.payment.service.inte.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository repository;

    private final PaymentMapper mapper;

    private final NotificationProducer notificationProducer;

    @Override
    public Integer createPayment(PaymentRequest request) {
        var payment = repository.save(mapper.toPayment(request));

        notificationProducer.sendNotification(new
                PaymentNotificationRequest(
                        request.orderReference(),
                request.amount(),
                request.paymentMethod(),
                request.customer().firstName(),
                request.customer().lastName(),
                request.customer().email()
        ));
        return payment.getId();
    }
}
