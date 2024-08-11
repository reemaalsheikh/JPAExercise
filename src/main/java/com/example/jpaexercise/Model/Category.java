package com.example.jpaexercise.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor

@Table(name="Categories")
public class Category {

    //• id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
   // @NotNull(message = "Category Id should not be Null!")
    private Integer categoryid;

    //• name (must not be empty, have to be more than 3 length long).
    @NotEmpty(message = "Category Name should not be Empty!")
    @Size(min = 4 , message = "Category name have to be more than 3 length long!")
    @Column(columnDefinition = "varchar(20) not null unique")
    private String categoryName;


}
