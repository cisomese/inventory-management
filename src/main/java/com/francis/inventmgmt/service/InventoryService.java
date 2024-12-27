package com.francis.inventmgmt.service;

import com.francis.inventmgmt.dto.CustomResponse;
import com.francis.inventmgmt.dto.DeleteResponse;
import com.francis.inventmgmt.dto.OrderAcceptance;
import com.francis.inventmgmt.dto.OrderDto;
import com.francis.inventmgmt.dto.ProductInsertionResponse;
import com.francis.inventmgmt.dto.SearchResponse;
import com.francis.inventmgmt.dto.UpdateProductResponse;
import com.francis.inventmgmt.model.Customer;
import com.francis.inventmgmt.model.Order;
import com.francis.inventmgmt.model.Products;
import com.francis.inventmgmt.repository.CustomerRepo;
import com.francis.inventmgmt.repository.OrdersRepo;
import com.francis.inventmgmt.repository.ProductsRepo;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InventoryService {
    @Autowired
    CustomerRepo customerRepo;
    @Autowired
    ProductsRepo productsRepo;
    @Autowired
    OrdersRepo ordersRepo;

    public CustomResponse createCustomer(Customer customer) {
        customerRepo.save(customer);
        return CustomResponse
                .builder()
                .status("success")
                .response("Customer created successfully")
                .build();
    }
    public ProductInsertionResponse createProduct(Products products){
        productsRepo.save(products);
        return ProductInsertionResponse.builder()
                        .status(true)
                        .message("product added sucessfully")
                        .build();
    }
    public UpdateProductResponse updateProduct(Long id,Products updatedProducts){
        Optional<Products> existedProd = productsRepo.findById(id);
        if(existedProd.isPresent()){
            Products updateProduct = existedProd.get();
            updateProduct.setItemName(updateProduct.getItemName());
            updateProduct.setPrice(updatedProducts.getPrice());

        }
        return UpdateProductResponse.builder()
                             .status(true)
                             .response("product updated successfully")
                             .build();
        
    }
    public OrderAcceptance createOrder(OrderDto orderdto){
            
        List<Products> pro = productsRepo.findAll();
        OrderAcceptance orderAcceptance = null;
    

        List<String> itemNames = orderdto.getItemName(); 
        List<Integer> quantities = orderdto.getQuantity(); 
    
        double totalPrice = 0.0;
        boolean allProductsAvailable = true;
    
        for (int i = 0; i < itemNames.size(); i++) {
            String itemName = itemNames.get(i);
            Integer quantity = quantities.get(i);
            boolean productFound = false;
    

            for (Products product : pro) {
                if (itemName.equals(product.getItemName())) {
                    productFound = true;
                    
                    Order order = new Order();
                    order.setProductName(itemNames.toString());
                    order.setQuantity(quantities.toString());
                    order.setCustomerName(orderdto.getCustomerName());
                    ordersRepo.save(order);
    
                    
                    totalPrice += quantity * product.getPrice();
                    break; 
                }
            }
            
            if (!productFound) {
                allProductsAvailable = false;
                break; 
            }
        }
    
        if (allProductsAvailable) {
            orderAcceptance = OrderAcceptance.builder()
                    .status(true)
                    .response("Your order was sent successfully.")
                    .totalPrice(totalPrice)
                    .build();
        } else {
            
            
            orderAcceptance = OrderAcceptance.builder()
                    .status(false)
                    .response("Sorry, one or more products are finished.")
                    .build();
        }
    
        return orderAcceptance;
    }
    public List<SearchResponse> searchProducts(String name, Double minPrice, Double maxPrice) {
        List<Products> products = productsRepo.findAll();
        return products.stream()
                .filter(product -> product.getItemName().contains(name) &&
                                  product.getPrice() >= minPrice &&
                                  product.getPrice() <= maxPrice)
                .map(product -> SearchResponse.builder()
                        .productName(product.getItemName()) 
                        .price(product.getPrice())
                        .message("product found")
                        .build())
                .collect(Collectors.toList());
    }
    public DeleteResponse deleteProd(Long id){
        Optional<Products> products= productsRepo.findById(id);
        DeleteResponse deleteResponse = null;
        if(products.isPresent()){
            productsRepo.deleteById(id);
        deleteResponse = DeleteResponse.builder()
                              .message("product deleted sucessfully")
                              .build();
    }
    else{
        deleteResponse = DeleteResponse.builder()
                                       .message("prodct not found")
                                       .build();
    }
    return deleteResponse;
}
public List<Order> showOrder(){
    //List<Order> order = ordersRepo.findAll();
    return ordersRepo.findAll();
}
}
