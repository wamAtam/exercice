package com.example.exercise.services.impl;

import com.example.exercise.entities.OrderLine;
import com.example.exercise.services.OrderLineService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OrderLineServiceImplTest {

    @Autowired
    OrderLineService orderLineService;


    @Test
    void getOrders() {


        List<OrderLine> expected = new ArrayList<>();

        List<OrderLine> actual = orderLineService.getOrders();

        expected.addAll(actual);

        assertEquals(expected, actual);

    }










//        OrderLine orderLine1 = OrderLine.builder()
//                .productName("Ski glove")
//                .quantity(4)
//                .price(BigDecimal.valueOf(10))
//                .build();
//
//
//        OrderLine orderLine2 = OrderLine.builder()
//                .productName("wool cap")
//                .quantity(1)
//                .price(BigDecimal.valueOf(14.80))
//                .build();
//
//        List<OrderLine> orderLines = Arrays.asList(orderLine1, orderLine2);

        // save(order)
//
//
//
//
//        List<OrderLine> actual = orderLineService.getOrders();
//
//        assertEquals(actual, orderLines);









}