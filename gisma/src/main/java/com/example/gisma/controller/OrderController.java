package com.example.gisma.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.example.gisma.dto.ApiResponse;
import com.example.gisma.dto.OrderDto;
import com.example.gisma.entity.Order;
import com.example.gisma.entity.OrderSearch;
import com.example.gisma.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/boutique/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    @ResponseBody
    public ResponseEntity<ApiResponse> uploadFile(final MultipartHttpServletRequest request,
                                                     @RequestParam("order") String orderStr) throws JsonParseException, JsonMappingException, IOException {
        Iterator<String> fileNames = request.getFileNames();
        List<MultipartFile> multipartFiles = new ArrayList<MultipartFile>();
        while (fileNames.hasNext()) {
            multipartFiles.add(request.getFile(fileNames.next()));
        }
        OrderDto orderDto = OrderDto.builder().files(multipartFiles)
                .order(new ObjectMapper().readValue(orderStr, Order.class)).build();
        orderService.saveOrder(orderDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse(HttpStatus.CREATED.value(), "Success"));
    }

    @PostMapping("/bag")
    @ResponseBody
    public ResponseEntity<ApiResponse> saveOrders(@RequestBody List<Order> orders) {
        for (Order order : orders) {
            OrderDto orderDto = OrderDto.builder().files(null).order(order).build();
            orderService.saveOrder(orderDto);
        }
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse(HttpStatus.CREATED.value(), "Success"));

    }

    @PatchMapping
    @ResponseBody
    public ResponseEntity<ApiResponse> updateOrder(@RequestBody Order order) {
        orderService.updateOrder(order);
        return ResponseEntity.ok(new ApiResponse(HttpStatus.OK.value(), "Updated"));    }

    @DeleteMapping
    @ResponseBody
    public ResponseEntity<ApiResponse> deleteOrder(@PathVariable Long orderId) {
        orderService.deleteOrder(orderId);
        return ResponseEntity.ok(new ApiResponse(HttpStatus.OK.value(), "Deleted"));
    }

    @GetMapping
    @ResponseBody
    public ResponseEntity<List<Order>> getOrders(@RequestParam(required = false) Long userId,
                                                 @RequestParam(required = false) String designType) {

        return ResponseEntity
                .ok(orderService.getOrders(OrderSearch.builder().userId(userId).designType(designType).build()));
    }
}

