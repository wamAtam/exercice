package com.example.exercise.services.impl;

import com.example.exercise.dao.OrderLineRepository;
import com.example.exercise.entities.OrderLine;
import com.example.exercise.services.OrderLineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class OrderLineServiceImpl implements OrderLineService {

    @Autowired
    private OrderLineRepository orderLineRepository;


    /**
     * {@inheritDoc}
     */

    @Override
    public OrderLine geOrderById(Long id) {
        Optional<OrderLine> res = orderLineRepository.findById(id);
        return res.isPresent() ? res.get() : null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<OrderLine> getOrders() {
        return orderLineRepository.findAll();
    }


}
