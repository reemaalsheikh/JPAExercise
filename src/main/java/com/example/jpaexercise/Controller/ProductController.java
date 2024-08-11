package com.example.jpaexercise.Controller;

import com.example.jpaexercise.Model.Product;
import com.example.jpaexercise.Service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/product")
public class ProductController {

    private final ProductService productService;

    @GetMapping("/get")
    public ResponseEntity getProducts() {
        return ResponseEntity.status(200).body(productService.getProducts());
    }

    @PostMapping("/add")
    public ResponseEntity addProduct(@Valid @RequestBody Product product, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        productService.addProduct(product);
        return ResponseEntity.status(200).body("Product Successfully added!");
    }


    @PutMapping("/update/{id}")
    public  ResponseEntity updateProduct(@PathVariable Integer id,@Valid @RequestBody  Product product , Errors errors){
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        boolean isUpdated = productService.UpdateProduct(id, product);
        if(isUpdated){
            return ResponseEntity.status(200).body("Product Successfully updated!");
        }
        return ResponseEntity.status(400).body("Product Not Found!");

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteProduct(@PathVariable Integer id){
        boolean isDeleted = productService.DeleteProduct(id);
        if(isDeleted){
            return ResponseEntity.status(200).body("Successfully deleted!");
        }
        return ResponseEntity.status(400).body("not found!");
    }

    @PutMapping("/buy/{userId}/{productId}/{merchantId}")
    public ResponseEntity buyProducts (@PathVariable Integer userId , @PathVariable Integer productId , @PathVariable Integer merchantId){
        int r1 = productService.buyProduct(userId, productId, merchantId);
        if(r1 == 0){
            return ResponseEntity.status(400).body("Product not found");
        }
        if (r1 == 1){
            return ResponseEntity.status(400).body("The product is not in stock");
        }
        if (r1 == 2) {
            return ResponseEntity.status(400).body("Error! balance is less than the product price.");
        }

        return ResponseEntity.status(200).body("Successfully bought the product");


    }


    @PutMapping("/discount/{userId}/{productId}")
    public ResponseEntity discount(@PathVariable Integer userId , @PathVariable Integer productId) {
        if (productService.discount(userId,productId).equalsIgnoreCase("Discount addedd successful")){
            return ResponseEntity.status(200).body("Discount added successfully");
        }
        return ResponseEntity.status(400).body(productService.discount(userId,productId));
    }

    @GetMapping("/topSeller")
    public ResponseEntity DisplayTopSeller(){
        if(productService.topSeller().isEmpty()){
            return ResponseEntity.status(400).body("Top Sellers products not found!");
        }
        return ResponseEntity.status(200).body(productService.topSeller());
    }


    @PostMapping("/add/{userId}/{productId}")
    public ResponseEntity AddReview (@PathVariable Integer userId ,@PathVariable Integer productId,@RequestBody String review){
        if (productService.Review(userId ,productId, review).equalsIgnoreCase("Review added successfully")){
            return ResponseEntity.status(200).body("Review added successfully");
        }
        return ResponseEntity.status(400).body(productService.Review(userId ,productId, review));
    }


}
