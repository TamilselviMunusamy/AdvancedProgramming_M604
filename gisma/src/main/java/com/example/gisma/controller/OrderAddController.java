package com.example.gisma.controller;

import com.example.gisma.entity.OrderAddress;
import com.example.gisma.service.OrderAddressService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/boutique/address")
public class OrderAddController {
        @Autowired
        private OrderAddressService orderAddressService;

        @PostMapping
        @ResponseBody
        public ResponseEntity<OrderAddress> save(@RequestBody OrderAddress orderAddres) {
            return ResponseEntity.ok(orderAddressService.save(orderAddres));
        }

        @DeleteMapping("/{addressId}")
        @ResponseBody
        public ResponseEntity<String> delete(@PathVariable @Valid Long addressId) {
            orderAddressService.delete(addressId);
            return ResponseEntity.ok("Deleted Successfully");
        }


        @GetMapping
        @ResponseBody
        public ResponseEntity<List<OrderAddress>> findByUserId(@RequestParam @Valid Long userId) {
            return ResponseEntity.ok(orderAddressService.findByUserId(userId));
        }

    }
