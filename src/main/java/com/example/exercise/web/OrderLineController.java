package com.example.exercise.web;
import com.example.exercise.entities.OrderLine;
import com.example.exercise.entities.Transaction;
import com.example.exercise.services.OrderLineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;




@RestController
@RequestMapping("/orders")
public class OrderLineController {


    @Autowired
    OrderLineService orderLineService;


    /**
     * get Order
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public OrderLine getOrder(@PathVariable Long id){

        return orderLineService.geOrderById(id);
    }


    /**
     * get all order
     * @return
     */
    @GetMapping
    public List<OrderLine> getAll() {
        return orderLineService.getOrders();
    }






}
