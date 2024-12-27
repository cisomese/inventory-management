package com.francis.inventmgmt.controller;

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
import com.francis.inventmgmt.service.InventoryService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;




@RestController
@RequestMapping("/inventory")
public class InventoryController {
    @Autowired
    InventoryService inventoryService;
    @PostMapping("/addCustomer")
    public ResponseEntity<CustomResponse> insertCustomer( @RequestBody Customer customer){
        return ResponseEntity.ok(inventoryService.createCustomer(customer));
    }
    @PostMapping("/createProd")
    public ResponseEntity<ProductInsertionResponse> createProd(@RequestBody Products products) {
        return ResponseEntity.ok(inventoryService.createProduct(products));
    }
    @PostMapping("/createOrder")
    public ResponseEntity<OrderAcceptance> createOr(@RequestBody 
    OrderDto orderDto) {
        
        
        return ResponseEntity.ok(inventoryService.createOrder(orderDto));
    }
    @PutMapping("/updateProd/{id}")
    public ResponseEntity<UpdateProductResponse> putMethodName(@PathVariable Long id, @RequestBody Products product) {
        //TODO: process PUT request
        
        return ResponseEntity.ok(inventoryService.updateProduct(id, product));
    }
    @GetMapping("searchProd")
    public ResponseEntity<List<SearchResponse>> getMethodName(@RequestParam String name,
                                                        @RequestParam double min_price,
                                                        @RequestParam double max_price) {
        return ResponseEntity.ok(inventoryService.searchProducts(name, min_price, max_price));
    }
    @DeleteMapping("/deletepro/{id}")
    public ResponseEntity<DeleteResponse> deleteProd(@PathVariable Long id){
        return ResponseEntity.ok(inventoryService.deleteProd(id));
    }
    @GetMapping("/showOrder")
    public List<Order> getOrderList() {
        return inventoryService.showOrder();
    }
    
    
    
    
}
