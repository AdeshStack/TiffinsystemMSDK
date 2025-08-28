package com.tiffinsystem.productservice.controller;


import com.tiffinsystem.productservice.Dao.ProductRequest;
import com.tiffinsystem.productservice.Dao.ProductResponse;
import com.tiffinsystem.productservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping()
    ResponseEntity<ProductResponse>Create(@RequestBody ProductRequest request){
        return new ResponseEntity<>(this.productService.create(request), HttpStatus.CREATED);
    }

    @PutMapping("/{productId}")
    ResponseEntity<ProductResponse>Update(@PathVariable("productId") Long productId,@RequestBody ProductRequest request){
        return new ResponseEntity<>(this.productService.update(productId,request),HttpStatus.OK);
    }

    @DeleteMapping("/{productId}")
    ResponseEntity<String> Delete(@PathVariable("productId") Long productId){
        this.productService.delete(productId);
        return new ResponseEntity<>("Product is successfully Deleted with this id:"+productId,HttpStatus.OK);
    }

    @GetMapping("/{productId}")
    ResponseEntity<ProductResponse>GetById(@PathVariable("productId") Long productId){

        return new ResponseEntity<>(this.productService.getById(productId),HttpStatus.OK);
    }

    @GetMapping("/all")
    ResponseEntity<List<ProductResponse>> GetAll(){

        return new ResponseEntity<>(this.productService.getAll(),HttpStatus.OK);
    }
}
