package com.example.jpaexercise.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor

@Table(name="Merchants")
public class Merchant {
    //• id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Integer  merchantid;

    //• name (must not be empty, have to be more than 3 length long).
    @NotEmpty(message = "Merchant Name should not be Empty!")
    @Size(min = 4, message = "Merchant Name have to be more than 3 length long")
    @Column(columnDefinition = "varchar(30) not null")
    private String  merchantName;


}
