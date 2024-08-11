package com.example.jpaexercise.Model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor

@Table(name="Products")
public class Product {


    //• id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Integer productId;

    //• name (must not be empty, have to be more than 3 length long).
    @NotEmpty(message = "Product Name should not be Empty!")
    @Size(min= 4 , message = "Product name have to be more than 3 length long.")
    @Column(columnDefinition = "varchar(20) not null ")
    private String productName;

    //• price (must not be empty, must be positive number).
    @NotNull(message = "Product Price should not be Null!")
    @Positive
    @Column(columnDefinition = "int not null")
    private int productPrice;

    //• categoryID (must not be empty).
    @NotNull(message = "Category Id should not be Null!")
    @Column(columnDefinition = "int not null unique")
    private Integer categoryID;

    @NotNull(message = "Top seller should not be Null!")
    @Column(columnDefinition = "int not null unique")
    private int topSeller=0;

    @NotEmpty(message = "Review should not be Empty!")
    @Column(columnDefinition = "varchar(20) not null ")
    private String review;

}
