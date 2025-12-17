package com.example.gisma.serviceimpl;


import java.util.List;

import com.example.gisma.entity.OrderAddress;
import com.example.gisma.repo.OrderAddressRepo;
import com.example.gisma.service.OrderAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class OrderAddressServiceImpl implements OrderAddressService {

    @Autowired
    private OrderAddressRepo orderAddressRepo;

    @Override
    public OrderAddress save(OrderAddress orderAddress) {
        return orderAddressRepo.save(orderAddress);
    }

    @Override
    public void delete(Long addressId) {
        orderAddressRepo.delete(orderAddressRepo.findById(addressId).orElse(null));

    }

    @Override
    public List<OrderAddress> findByUserId(Long userId) {
        return orderAddressRepo.findByUserId(userId);
    }
}