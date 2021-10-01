package com.example.exercise.web;
import com.example.exercise.entities.Transaction;
import com.example.exercise.exception.TransactionException;
import com.example.exercise.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
@RequestMapping("/transactions")
public class TransactionController {


    @Autowired
    private TransactionService transactionService;


    /**
     * get One Payment
     *
     * @param id
     * @return Transaction
     */
    @GetMapping("/{id}")
    public Transaction getOnePayment(@PathVariable Long id){

        return transactionService.gePaymentById(id);
    }


    /**
     * get All Payments
     *
     * @return Transaction List
     */
    @GetMapping
    public List<Transaction> getAllPayments(){

        return transactionService.getPayments();
    }


    /**
     * save the Payment
     *
     * @param transaction
     * @return transaction
     * @throws TransactionException
     */
    @PostMapping
    public Transaction savePayment(@RequestBody Transaction transaction) throws TransactionException {
        return  transactionService.savePayment(transaction);
    }


    /**
     * Update the payment
     *
     * @param transaction
     * @param id
     * @return transaction
     * @throws TransactionException
     */
    @PutMapping(value ="/{id}")
    public Transaction updatePayment(@RequestBody Transaction transaction,
                                     @PathVariable Long id)
            throws TransactionException{

        transaction.setId(id);

        return   transactionService.savePayment(transaction);
    }


}
