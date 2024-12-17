package com.viggad.order.service.impl;

import com.viggad.order.dto.request.OrderLineRequest;
import com.viggad.order.dto.response.OrderLineResponse;
import com.viggad.order.mapper.OrderLineMapper;
import com.viggad.order.repository.OrderLineRepository;
import com.viggad.order.service.inte.OrderLineService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderLineServiceImpl implements OrderLineService {

    private final OrderLineRepository orderLineRepository;

    private final OrderLineMapper mapper;

    @Override
    public Integer saveOrderLine(OrderLineRequest orderLineRequest) {
        var order = mapper.toOrderLine(orderLineRequest);
        return orderLineRepository.save(order).getId();
    }

    @Override
    public List<OrderLineResponse> findAllById(Integer id) {
        return orderLineRepository.findAllByOrderId(id)
                .stream()
                .map(mapper::toOrderLineResponse)
                .collect(Collectors.toList());
    }
}
