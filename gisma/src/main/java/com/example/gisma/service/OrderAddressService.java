package com.example.gisma.service;


import com.example.gisma.entity.OrderAddress;

import java.util.List;


public interface OrderAddressService {

    OrderAddress save(OrderAddress orderAddress);

    void delete(Long addressId);

    List<OrderAddress> findByUserId(Long userId);
}
