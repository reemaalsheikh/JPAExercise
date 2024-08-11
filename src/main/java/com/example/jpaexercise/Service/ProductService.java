package com.example.jpaexercise.Service;

import com.example.jpaexercise.Model.MerchantStock;
import com.example.jpaexercise.Model.Product;
import com.example.jpaexercise.Model.User;
import com.example.jpaexercise.Repository.MerchantStockRepository;
import com.example.jpaexercise.Repository.ProductRepository;
import com.example.jpaexercise.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final MerchantStockRepository merchantStockRepository;

    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    public void addProduct(Product product) {
        productRepository.save(product);
    }

    public boolean UpdateProduct(Integer id, Product product) {
        Product OldProduct = productRepository.getById(id);

        if (OldProduct == null) {
            return false;
        }
        OldProduct.setProductName(product.getProductName());
        OldProduct.setProductPrice(product.getProductPrice());
        OldProduct.setCategoryID(product.getCategoryID());

        productRepository.save(OldProduct);
        return true;
    }

    public boolean DeleteProduct(Integer id) {
     Product OldProduct = productRepository.getById(id);
     if (OldProduct == null) {
         return false;
     }
     productRepository.delete(OldProduct);
     return true;
    }


    public int buyProduct(Integer userId, Integer productId, Integer merchantId) {
        Product Oldproduct = productRepository.getById(productId);
        User user = userRepository.getById(userId);
        MerchantStock merchantStock = merchantStockRepository.getById(merchantId);

        if (Oldproduct != null && user != null && merchantStock != null) {


            //• check if the merchant has the product in stock or return bad request.
            if (merchantStock.getStock() > 0) {
                //• reduce the stock from the MerchantStock.
                merchantStock.setStock(merchantStock.getStock() - 1);
                merchantStockRepository.save(merchantStock);
            } else {
                return 1;//The product is not in stock
            }

            //• deducted the price of the product from the user balance.
            if (user.getBalance() >= Oldproduct.getProductPrice()) {
                user.setBalance(user.getBalance() - Oldproduct.getProductPrice());
                userRepository.save(user);

                Oldproduct.setProductPrice(Oldproduct.getTopSeller()+1);
                productRepository.save(Oldproduct);

                List<Product> buyList = user.getListOfProducts();
                if (buyList == null) {
                    buyList = new ArrayList<>();
                }
                buyList.add(Oldproduct);
                user.setListOfProducts(buyList);
                productRepository.saveAll(buyList);


            } else {
                return 2;//Error! balance is less than the product price
            }

            return 3;
        }

        return 0;//not found

    }


    public String discount(Integer userId , Integer productId){
        User user = userRepository.getById(userId);
        Product p = productRepository.getById(productId);

        if (user == null || user.getRole().equals("customer")) {
            return "User not allowed to make discount";
        }
        if ( p != null && p.getProductPrice() >= 900) {
            p.setProductPrice((int) (p.getProductPrice() - p.getProductPrice()*0.15));
            productRepository.save(p);
            return "Discount added successfully!";
        }

        return "Discount not added";
    }


    public List<Product> topSeller (){
        List<Product> Allproducts = productRepository.findAll();
        List<Product> topSeller = new ArrayList<>();
        for (Product product : Allproducts) {
            if (product.getTopSeller() >=4) {
                topSeller.add(product);
            }
        }
        return topSeller;
    }

    public String Review (Integer userId , Integer productId, String review){
        User u = userRepository.getById(userId);
        Product p = productRepository.getById(productId);
        if (u == null) {
            return "User not found";
        }
        if(u.getListOfProducts().isEmpty()){
            return "Your list is empty";
        }
        for (Product product : u.getListOfProducts()) {
            if (product.getProductId().equals(productId)) {
                product.setReview(review);
                productRepository.save(product);
                return "Review added successfully!";
            }
        }

        return "Review not added";

    }







}
