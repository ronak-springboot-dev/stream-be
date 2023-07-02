package com.stream.service;

import com.stream.model.Product;
import com.stream.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ExpressionException;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;


    public List<Product> getAllCustomer() {
        return productRepository.findAll();
    }


    public Product createCustomer(Product product) {
        return productRepository.save(product);
    }


    public Product getCustomerById(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new ExpressionException("Customer is not found !"));
    }


    //find product with category having "Mobile" and price > 50000
    public List<Product> productWithCategoryMobileAndPriceLimitation() {

        return getAllCustomer()
                .stream()
                .filter(p -> p.getCategory().equalsIgnoreCase("Mobile"))
                .filter(p -> p.getPrice() > 50000)
                .collect(Collectors.toList());
    }

    //return product with the given discount price
    public List<Product> getProductWithDiscount(String category, int discount) {
        return productRepository.findAll()
                .stream()
                .filter(p -> p.getCategory().equalsIgnoreCase(category))
                .map(p -> new Product(p.getName(), p.getPrice() - p.getPrice() * discount / 100, p.getCategory()))
                .collect(Collectors.toList());
    }


    //find the cheapest product by category

    public Product getCheapestProductByCategory(String category) {
        return productRepository.findAll().stream()
                .filter(p -> p.getCategory().equalsIgnoreCase(category))
                .sorted(Comparator.comparing(Product::getPrice))
                .findFirst().get();
    }
    //get collection of static figures i.e sum, min, max, avg

    public DoubleSummaryStatistics getStatisticsByCategory(String category) {

        return productRepository.findAll()
                .stream()
                .filter(c -> c.getCategory().equalsIgnoreCase(category))
                .mapToDouble(p -> p.getPrice())
                .summaryStatistics();
    }


    public Map<String, List<String>> getAllProductNameByCategory(String category) {
        return productRepository.findAll()
                .stream()
                .filter(p -> p.getCategory().equalsIgnoreCase(category))
                .collect(
                        Collectors.groupingBy(
                                Product::getCategory,
                                Collectors.mapping(product -> product.getName(), Collectors.toList())
                        )
                );
    }


    public Map<String, Optional<Product>> getMostExpensiveProductByCategory(String category) {
        return productRepository.findAll()
                .stream()
                .filter(p->p.getCategory().equalsIgnoreCase(category))
                .collect(
                        Collectors.groupingBy(
                                Product::getCategory,
                                Collectors.maxBy(Comparator.comparing(Product::getPrice))
                        )
                );
    }

}
