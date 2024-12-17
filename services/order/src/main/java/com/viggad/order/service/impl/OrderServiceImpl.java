package com.viggad.order.service.impl;

import com.viggad.order.customer.CustomerClient;
import com.viggad.order.dto.request.OrderLineRequest;
import com.viggad.order.dto.request.OrderRequest;
import com.viggad.order.dto.request.PaymentRequest;
import com.viggad.order.dto.response.OrderResponse;
import com.viggad.order.exception.BusinessException;
import com.viggad.order.kafka.OrderConfirmation;
import com.viggad.order.kafka.OrderProducer;
import com.viggad.order.mapper.OrderMapper;
import com.viggad.order.payment.PaymentClient;
import com.viggad.order.product.ProductClient;
import com.viggad.order.product.PurchaseRequest;
import com.viggad.order.repository.OrderRepository;
import com.viggad.order.service.inte.OrderLineService;
import com.viggad.order.service.inte.OrderService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final CustomerClient customerClient;

    private final ProductClient productClient;

    private final OrderRepository orderRepository;

    private final OrderMapper mapper;

    private final OrderLineService orderLineService;

    private final OrderProducer orderProducer;

    private final PaymentClient paymentClient;
    @Override
    public Integer createOrder(OrderRequest orderRequest) {
        //check the customer --> OpenFeign
        var customer = this.customerClient.findByCustomerById(orderRequest.customerId())
                .orElseThrow(() -> new BusinessException("Cannot create order:: No Customer exists with the provided customer id"));

        //purchase the products --> product-ms (RestTemplate)

        var purchaseProducts = this.productClient.purchaseProduct(orderRequest.products());
        var order = this.orderRepository.save(mapper.toOrder(orderRequest));

        for(PurchaseRequest purchaseRequest : orderRequest.products()){
            orderLineService.saveOrderLine(
                    new OrderLineRequest(
                            null,
                            order.getId(),
                            purchaseRequest.productId(),
                            purchaseRequest.quantity()
                    )
            );
        }
        var paymentRequest = new PaymentRequest(
                orderRequest.amount(),
                orderRequest.paymentMethod(),
                order.getId(),
                order.getReference(),
                customer
        );
        paymentClient.requestOrderPayment(paymentRequest);

        orderProducer.sendOrderConfirmation(new OrderConfirmation(
                orderRequest.reference(),
                orderRequest.amount(),
                orderRequest.paymentMethod(),
                customer,
                purchaseProducts

        ));

        return order.getId();
    }

    @Override
    public List<OrderResponse> findAll() {
        return orderRepository.findAll()
                .stream()
                .map(mapper::fromOrder)
                .collect(Collectors.toList());
    }

    @Override
    public OrderResponse findById(Integer id) {
        return orderRepository.findById(id)
                .map(mapper::fromOrder)
                .orElseThrow(() -> new EntityNotFoundException("No order found with the id"));
    }
}
