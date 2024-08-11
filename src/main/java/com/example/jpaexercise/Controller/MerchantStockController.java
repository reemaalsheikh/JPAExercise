package com.example.jpaexercise.Controller;

import com.example.jpaexercise.Model.MerchantStock;
import com.example.jpaexercise.Service.MerchantStockService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/merchantStock")
public class MerchantStockController {

    private final MerchantStockService merchantStockService;

    @GetMapping("/get")
    public ResponseEntity getMerchantStock() {
        return ResponseEntity.status(200).body(merchantStockService.getMerchantStock());
    }

    @PostMapping("/add")
    public ResponseEntity addMerchantStock (@Valid @RequestBody MerchantStock merchantStock , Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        merchantStockService.addMerchantStock(merchantStock);
        return ResponseEntity.status(200).body("MerchantStock Successfully added!");
    }

    @PutMapping("/update/{id}")
    public  ResponseEntity updateMerchantStock(@PathVariable Integer id,@Valid @RequestBody MerchantStock merchantStock , Errors errors){
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        boolean isUpdated = merchantStockService.updateMerchantStock(id,merchantStock);
        if(isUpdated){
            return ResponseEntity.status(200).body("MerchantStock Successfully updated!");
        }
        return ResponseEntity.status(400).body("MerchantStock not found!");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteMerchantStock(@PathVariable Integer id) {
        boolean isDeleted = merchantStockService.deleteMerchantStock(id);
        if(isDeleted){
            return ResponseEntity.status(200).body("MerchantStock Successfully deleted!");
        }
        return ResponseEntity.status(400).body("MerchantStock not found!");
    }

    @PutMapping("/stock/{productId}/{MerchantId}/{MerchantStockId}/{stock}")
    public ResponseEntity addStock(@PathVariable Integer productId,@PathVariable Integer MerchantId,
                                   @PathVariable Integer MerchantStockId,@PathVariable int stock ) {
        boolean isUpdated = merchantStockService.addStock(productId,MerchantId,MerchantStockId,stock);
        if (isUpdated) {
            return ResponseEntity.status(200).body("Stock Added Successfully");
        }

        return ResponseEntity.status(400).body("Stock Not Found");
    }






}
