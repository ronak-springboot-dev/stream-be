package com.stream.controller;


import com.stream.model.Customer;
import com.stream.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping
    public Customer createCustomer(@RequestBody Customer customer){
        return customerService.createCustomer(customer);
    }

    @GetMapping
    public List<Customer> findAllCustomer(){
        return customerService.getAllCustomer();
    }

    @GetMapping("{/id}")
    public Customer findCustomerById(@PathVariable Long id){
        return customerService.getCustomerById(id);
    }
}
