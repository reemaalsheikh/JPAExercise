package com.example.jpaexercise.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor

public class MerchantStock {

    //• id (must not be empty).
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
   // @NotNull(message = "Merchant Stock Id should not be Null!")
    private Integer merchantStockId;


    //• productid (must not be empty).
    @NotNull(message = "Product Id should not be Null!")
    @Column(columnDefinition = "int not null unique")
    private Integer productId;

    //• merchantid
    @NotNull(message = "Merchant Id should not be Null!")
    @Column(columnDefinition = "int not null unique")
    private Integer merchantId;

    //• stock (must not be empty, have to be more than 10 at start).
    @NotNull(message = "Stock should not be Null!")
    @Min(value=10)
    @Column(columnDefinition = "int not null")
    private int stock;
}
