package com.viggad.order.repository;

import com.viggad.order.orderline.OrderLine;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface OrderLineRepository extends JpaRepository<OrderLine, Integer> {

    List<OrderLine> findAllByOrderId(Integer orderId);

}
