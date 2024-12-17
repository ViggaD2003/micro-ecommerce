package com.viggad.order.service.inte;

import com.viggad.order.dto.request.OrderRequest;
import com.viggad.order.dto.response.OrderResponse;

import java.util.List;
public interface OrderService {

    Integer createOrder(OrderRequest orderRequest);

    List<OrderResponse> findAll();

    OrderResponse findById(Integer id);
}
