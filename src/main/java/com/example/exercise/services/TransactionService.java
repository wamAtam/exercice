package com.example.exercise.services;

import com.example.exercise.entities.Transaction;
import com.example.exercise.exception.TransactionException;

import java.util.List;

public interface TransactionService {

    /**
     * ge Payment By Id
     *
     * @param id
     * @return
     */
    Transaction gePaymentById(Long id);


    /**
     *
     * @return
     */
    List<Transaction> getPayments();

    /**
     * save Payment
     *
     * @param transaction
     * @return
     * @throws TransactionException
     */
    Transaction savePayment(Transaction transaction) throws TransactionException;

}
