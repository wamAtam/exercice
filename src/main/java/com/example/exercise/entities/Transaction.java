package com.example.exercise.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Transaction implements Serializable {


    @Id
    @GeneratedValue
    private Long id;

    private BigDecimal amount;

    private  PaymentType paymentType;

    @OneToMany(mappedBy = "transaction", cascade = CascadeType.ALL)
    private List<OrderLine> orderLines = new ArrayList<>();

    private Status status;



    @AllArgsConstructor
    @Getter
    public enum PaymentType {
        CREDIT_CARD,
        GIFT_CARD,
        PAYPAL;
    }


    @AllArgsConstructor
    @Getter
    public enum Status {
        NEW,
        AUTHORIZED,
        CAPTURED;
    }
}
