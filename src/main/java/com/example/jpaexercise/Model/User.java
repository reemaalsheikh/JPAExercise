package com.example.jpaexercise.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor

@Table(name="Users")
public class User {


    //• id (must not be empty).
    //Primary key
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Integer id;

    //• username (must not be empty, have to be more than 5 length long).
    @NotEmpty(message = "User name should not be Empty!")
    @Size(min=6, message = "User name have to be more than 5 length long!")
   // @Column(columnDefinition = "varchar(20) not null unique")
    private String username;

    //• password (must not be empty, have to be more than 6 length long, must have characters and digits).
    @NotEmpty(message = "Password should not be Empty!")
    @Size(min=6)
    @Pattern(regexp = "^((?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{7,})$" , message = "Password must have to be more than 6 length long,characters and digits")
   // @Column(columnDefinition = "varchar(30)not null check (Password REGEXP '^[0-9]{10,15}$')")
    private String password;

    //• email (must not be empty, must be valid email).
    @NotEmpty(message = "Email should not be Empty!")
    @Email
    @Column(columnDefinition = "varchar(30) not null unique")
    private String email;

    //• role (must not be empty, have to be in ( “Admin”,”Customer”)).
    @NotEmpty(message = "Role should not be Empty!")
    @Pattern(regexp = "^(admin|customer)$", message = "Role inputs has to be: admin or customer ONLY!")
   // @Column(columnDefinition = "varchar(10) not null check(role='admin' or role = 'customer')")
    private String role;


//• balance (must not be empty, have to be positive).
    @NotNull(message = "Balance should not be Null!")
    @Positive()
    @Column(columnDefinition = "int not null")
    private int balance;


    private List<Product> listOfProducts = new ArrayList<>();




}
