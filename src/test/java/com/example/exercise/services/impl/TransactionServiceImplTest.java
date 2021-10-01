package com.example.exercise.services.impl;

import com.example.exercise.entities.OrderLine;
import com.example.exercise.entities.Transaction;
import com.example.exercise.exception.TransactionException;
import com.example.exercise.services.TransactionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TransactionServiceImplTest {

    @Autowired
    TransactionService transactionService;


    @Test
    void gePaymentById() throws TransactionException {

        Transaction transaction = Transaction.builder().id(1L).build();

        transactionService.savePayment(transaction);

        Transaction actual = transactionService.gePaymentById(transaction.getId());

        assertEquals(Long.valueOf(1L), actual.getId());

    }



    @Test
    void getPayments() {

        List<Transaction> expected = new ArrayList<>();

        List<Transaction> actual = transactionService.getPayments();

        expected.addAll(actual);

        assertEquals(expected, actual);

    }

    @Test
    void savePaymentWithCreditCard() throws TransactionException {


        OrderLine orderLine1 = OrderLine.builder()
                .productName("Ski glove")
                .quantity(4)
                .price(BigDecimal.valueOf(10))
                .build();

        OrderLine orderLine2 = OrderLine.builder()
                .productName("wool cap")
                .quantity(1)
                .price(BigDecimal.valueOf(14.80))
                .build();

        List<OrderLine> orderLineList = Arrays.asList(orderLine1, orderLine2);

        Transaction transaction = Transaction.builder().id(null)
                        .amount(BigDecimal.valueOf(54.80)).
                paymentType(Transaction.PaymentType.CREDIT_CARD)
                .orderLines(orderLineList).build();


        transactionService.savePayment(transaction);

        assertNotNull(transaction.getId());


    }



    @Test
    void savePaymentWithPaypal() throws TransactionException {



        OrderLine orderLine = OrderLine.builder()
                .productName("Bike")
                .quantity(1)
                .price(BigDecimal.valueOf(208))
                .build();

        List<OrderLine> orderLineList = Arrays.asList(orderLine);

        Transaction transaction = Transaction.builder().id(null)
                        .amount(BigDecimal.valueOf(208)).
                paymentType(Transaction.PaymentType.PAYPAL)
                .orderLines(orderLineList).build();


        transactionService.savePayment(transaction);

        assertNotNull(transaction.getId());


    }

    @Test
    void savePaymentWithOutStatus() throws TransactionException {

        Transaction transaction = Transaction.builder().status(null).build();

        transactionService.savePayment(transaction);

        assertTrue(transaction.getStatus().equals(Transaction.Status.NEW));


    }


    @Test()
    void saveNewPaymentWithStatusDifferentToNew()  {


        Transaction transaction = Transaction.builder().status(Transaction.Status.AUTHORIZED).build();

        assertThatThrownBy(()-> transactionService.savePayment(transaction))
                .isInstanceOf(TransactionException.class)
                        .hasMessageContaining("NEW transaction must be set with NEW status");

    }






    @Test
    void updatePaymentWithCorrectStatus() throws TransactionException {

        Transaction transaction = Transaction.builder().status(Transaction.Status.NEW).build();
        transactionService.savePayment(transaction);

        transaction.setStatus(Transaction.Status.AUTHORIZED);
        transactionService.savePayment(transaction);

        transaction.setStatus(Transaction.Status.CAPTURED);
        transactionService.savePayment(transaction);


        assertTrue(transaction != null && Transaction.Status.CAPTURED.equals(transaction.getStatus()));


    }



    @Test
    void updatePaymentWithoutAuthorized() throws TransactionException {

        Transaction transaction = Transaction.builder().status(Transaction.Status.NEW).build();
        transactionService.savePayment(transaction);

        transaction.setStatus(Transaction.Status.CAPTURED);

        assertThatThrownBy(()-> transactionService.savePayment(transaction))
                .isInstanceOf(TransactionException.class)
                .hasMessageContaining("can not pass to the CAPTURED " +
                        "the transaction is not in the AUTHORIZED state");
    }


    @Test
    void updatePaymentWithoutCapturedStatus() throws TransactionException {

        Transaction transaction = Transaction.builder().status(Transaction.Status.NEW).build();
        transactionService.savePayment(transaction);

        transaction.setStatus(Transaction.Status.AUTHORIZED);
        transactionService.savePayment(transaction);

        transaction.setStatus(Transaction.Status.CAPTURED);
        transactionService.savePayment(transaction);

        transaction.setStatus(Transaction.Status.NEW);


        assertThatThrownBy(()-> transactionService.savePayment(transaction))
                .isInstanceOf(TransactionException.class)
                .hasMessageContaining("is not allowed to change CAPTURED status");

    }



}