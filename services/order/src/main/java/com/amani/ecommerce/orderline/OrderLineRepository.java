package com.amani.ecommerce.orderline;

import com.ctc.wstx.util.ElementId;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderLineRepository extends JpaRepository<OrderLine, Integer> {

    List<OrderLine> findAllByOrderId(Integer orderId);
}