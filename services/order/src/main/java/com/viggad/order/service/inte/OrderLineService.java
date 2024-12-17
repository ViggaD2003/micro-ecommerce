package com.viggad.order.service.inte;

import com.viggad.order.dto.request.OrderLineRequest;
import com.viggad.order.dto.response.OrderLineResponse;

import java.util.List;
public interface OrderLineService {

    Integer saveOrderLine(OrderLineRequest orderLineRequest);

    List<OrderLineResponse> findAllById(Integer id);
}
