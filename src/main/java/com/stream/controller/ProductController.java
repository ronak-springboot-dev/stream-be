package com.stream.controller;

import com.stream.model.Product;
import com.stream.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        return productService.createCustomer(product);
    }

    @GetMapping
    public List<Product> findAllProduct() {
        return productService.getAllCustomer();
    }

    @GetMapping("{/id}")
    public Product findProductById(@PathVariable Long id) {
        return productService.getCustomerById(id);
    }


    @GetMapping("/categoryAndPrice")
    public List<Product> productWithCategoryMobile() {
        return productService.productWithCategoryMobileAndPriceLimitation();
    }


    @GetMapping("/productWithDiscount")
    public List<Product> getProductWithDiscount(@RequestParam String category, @RequestParam int discount) {
        return productService.getProductWithDiscount(category, discount);
    }

    @GetMapping("/cheapestProductBycategory")
    public Product getCheapestProductBycategory(@RequestParam String category) {
        return productService.getCheapestProductByCategory(category);
    }

    @GetMapping("/statistics")
    public DoubleSummaryStatistics getStatisticsByCategory(@RequestParam String category) {
        return productService.getStatisticsByCategory(category);
    }

    @GetMapping("/productNameByCategory")
    public Map<String, List<String>> getAllProductNameByCategory(@RequestParam String category) {
        return productService.getAllProductNameByCategory(category);
    }
    @GetMapping("/mostExpensive")
    public Map<String, Optional<Product>> getMostExpensiveProductByCategory(@RequestParam String category){
        return productService.getMostExpensiveProductByCategory(category);
    }








}
