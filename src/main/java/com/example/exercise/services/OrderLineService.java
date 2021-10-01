package com.example.exercise.services;

import com.example.exercise.entities.OrderLine;


import java.util.List;



public interface OrderLineService {


    /**
     * ge Order By Id
     *
     * @param id
     * @return
     */
    OrderLine geOrderById(Long id);



    /**
     * get All Orders
     *
     * @return
     */
    List<OrderLine> getOrders();




}
