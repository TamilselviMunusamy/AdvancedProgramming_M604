package com.example.gisma.serviceimpl;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.example.gisma.dto.OrderDto;
import com.example.gisma.entity.Order;
import com.example.gisma.entity.OrderAddress;
import com.example.gisma.entity.OrderSearch;
import com.example.gisma.enumaration.OrderStatus;
import com.example.gisma.exception.CustomException;
import com.example.gisma.repo.BoutiqueProductRepo;
import com.example.gisma.repo.OrderAddressRepo;
import com.example.gisma.repo.OrderRepo;
import com.example.gisma.repo.UserRepo;
import com.example.gisma.service.OrderService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.CollectionUtils;
import org.springframework.cglib.core.Predicate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private OrderAddressRepo orderAddressRepo;

    @Autowired
    private BoutiqueProductRepo boutiqueProductRepo;


//    @Autowired
//    private ApiUtils utils;

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    @Override
    public void saveOrder(OrderDto orderDto) {
        try {
            List<MultipartFile> multipartFiles = orderDto.getFiles();
            Order order = orderDto.getOrder();
            order.setUser(userRepo.findById(order.getUser().getId()).orElse(null));
            order.setOrderAddress(address(order));
            order.setBoutiqueProduct(
                    boutiqueProductRepo.findByTitle(order.getBoutiqueProduct().getTitle()).orElse(null));
            order.setStatus(OrderStatus.PEDNING.getStatusStr());
            order.setCreatedOn(new Date());
            order = orderRepo.save(order);
            //setEmailPrams(multipartFiles, order);
            log.info("succesfully ordered");
        } catch (Exception e) {
            throw new CustomException(e.getMessage());
        }
    }

    private OrderAddress address(Order order) {
        if (Objects.isNull(order.getOrderAddress()) || Objects.isNull(order.getOrderAddress().getAddress1())) {
            return null;
        }
        OrderAddress orderAddress = orderAddressRepo.findFirstByAddress1AndCityAndStateAndPinCode(
                order.getOrderAddress().getAddress1(), order.getOrderAddress().getCity(),
                order.getOrderAddress().getState(), order.getOrderAddress().getPinCode()).orElse(null);
        if (Objects.nonNull(orderAddress)) {
            order.getOrderAddress().setId(orderAddress.getId());
        }
        if (Objects.isNull(order.getOrderAddress()) || order.getOrderAddress().getId() == null) {
            order.getOrderAddress().setCreatedAt(new Date());
            orderAddress = orderAddressRepo.save(order.getOrderAddress());
        } else {
            orderAddress = orderAddressRepo.findById(order.getOrderAddress().getId()).orElse(null);
            orderAddress.setUpdatedAt(new Date());
            orderAddress = orderAddressRepo.save(orderAddress);
        }
        return orderAddress;
    }

    @Transactional
    @Override
    public void updateOrder(Order order) {
        if (!Objects.isNull(order.getUser())) {
            order.setUser(userRepo.findById(order.getUser().getId()).orElse(null));
        }
        orderRepo.save(order);
    }
    @Override
    public List<Order> getOrders(OrderSearch orderSearch) {
        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Order> criteriaQuery = criteriaBuilder.createQuery(Order.class);
            Root<Order> orderItem = criteriaQuery.from(Order.class);
            criteriaQuery.select(orderItem);
            List<Predicate> predicates = new ArrayList<>();
            if (Objects.nonNull(orderSearch.getUserId())) {
                predicates.add((Predicate) orderItem.get("user").get("id").in(orderSearch.getUserId()));
            }
            if (Objects.nonNull(orderSearch.getDesignType())) {
                predicates.add((Predicate) orderItem.get("design").get("designType").in(orderSearch.getDesignType()));
            }
            TypedQuery<Order> query = entityManager.createQuery(criteriaQuery);
            return query.getResultList().stream().sorted(Comparator.comparing(Order::getId).reversed())
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new CustomException(e.getMessage());
        }
    }

    public void deleteOrder(Long orderId) {
        orderRepo.delete(orderRepo.findById(orderId).orElse(null));

    }
}

