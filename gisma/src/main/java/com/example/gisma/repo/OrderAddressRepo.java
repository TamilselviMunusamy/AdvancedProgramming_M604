package com.example.gisma.repo;

import com.example.gisma.entity.OrderAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface OrderAddressRepo extends JpaRepository<OrderAddress, Long> {
    List<OrderAddress> findByUserId(Long userId);

    Optional<OrderAddress> findFirstByAddress1AndCityAndStateAndPinCode(String address1, String city, String state, String pinCode);

}
