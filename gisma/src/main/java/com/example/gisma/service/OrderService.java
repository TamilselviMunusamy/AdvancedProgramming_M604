package com.example.gisma.service;

import com.example.gisma.dto.OrderDto;
import com.example.gisma.entity.Order;
import com.example.gisma.entity.OrderSearch;

import java.util.List;


public interface OrderService {

    void saveOrder(OrderDto orderDto);

    void updateOrder(Order order);

    void deleteOrder(Long orderId);

    List<Order> getOrders(OrderSearch orderSearch);
}
