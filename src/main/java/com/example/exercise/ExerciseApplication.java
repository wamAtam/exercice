package com.example.exercise;

import com.example.exercise.dao.TransactionRepository;
import com.example.exercise.entities.OrderLine;
import com.example.exercise.entities.Transaction;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class ExerciseApplication {



    public static void main(String[] args) {


        SpringApplication.run(ExerciseApplication.class, args);
    }





}
