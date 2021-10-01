package com.example.exercise.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class OrderLine implements Serializable {


    @Id
    @GeneratedValue
    private Long id;

    private String productName;

    private int quantity;

    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "ID_TRANSACTION")
    private Transaction transaction;



}
