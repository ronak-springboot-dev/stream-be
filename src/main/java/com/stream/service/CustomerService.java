package com.stream.service;

import com.stream.model.Customer;
import com.stream.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ExpressionException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;


    public List<Customer> getAllCustomer(){
        return customerRepository.findAll();
    }



    public Customer createCustomer(Customer customer){
        return customerRepository.save(customer);
    }


    public Customer getCustomerById(Long id){
        return customerRepository.findById(id).orElseThrow(()-> new ExpressionException("Customer is not found !"));
    }
}
