package com.example.exercise.services.impl;

import com.example.exercise.dao.TransactionRepository;
import com.example.exercise.entities.Transaction;
import com.example.exercise.exception.TransactionException;
import com.example.exercise.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;


    /**
     * {@inheritDoc}
     */
    @Override
    public Transaction gePaymentById(Long id) {

        Optional<Transaction> res = transactionRepository.findById(id);

        return res.isPresent() ? res.get(): null;
    }



    /**
     * {@inheritDoc}
     */
    @Override
    public List<Transaction> getPayments() {
        return transactionRepository.findAll();
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public Transaction savePayment(Transaction transaction) throws TransactionException {

        Transaction oldTransaction = null;

        if (transaction.getId() != null){
            oldTransaction = gePaymentById(transaction.getId());
        }
        checkStatus(oldTransaction, transaction);

        return transactionRepository.save(transaction);
    }


    /**
     * check Transaction Status
     *
     * @param oldTransaction
     * @param newTransaction
     * @throws TransactionException
     */
    public  void checkStatus(Transaction oldTransaction, Transaction newTransaction ) throws TransactionException{


        // creation Mode
        if(oldTransaction == null) {
            if (newTransaction.getStatus() == null) {
                newTransaction.setStatus(Transaction.Status.NEW);
            }
            if (!Transaction.Status.NEW.equals(newTransaction.getStatus())) {
                throw new TransactionException("NEW transaction must be set with NEW status");
            }

        // update Mode
        } else {
            if(!Transaction.Status.AUTHORIZED.equals(oldTransaction.getStatus()) &&
                    Transaction.Status.CAPTURED.equals(newTransaction.getStatus())){

                throw new TransactionException("can not pass to the CAPTURED " +
                        "the transaction is not in the AUTHORIZED state");
            }
            if(Transaction.Status.CAPTURED.equals(oldTransaction.getStatus())){
                throw new TransactionException("is not allowed to change CAPTURED status");
            }
        }

    }



}
