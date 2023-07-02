package com.stream.service;

import com.stream.model.Customer;
import com.stream.model.Order;
import com.stream.model.Product;
import com.stream.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ExpressionException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class OrderService {


    @Autowired
    private OrderRepository orderRepository;


    public List<Order> getAllOrder() {
        return orderRepository.findAll();
    }


    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }


    public Order getOrderById(Long id) {
        return orderRepository.findById(id).orElseThrow(() -> new ExpressionException("Order is not found !"));
    }


    //find order with given product category
    public List<Order> findOrderByProductCategory(String productCategory) {
        return getAllOrder()
                .stream()
                .filter(o -> o.getProducts().stream().anyMatch(p -> p.getCategory().equalsIgnoreCase(productCategory)))
                .collect(Collectors.toList());
    }


    //find product order between given date
    public List<Product> findOrderBasedOnGivenDate(LocalDate startDate, LocalDate endDate, int tier) {
        return getAllOrder()
                .stream()
                .filter(c -> c.getCustomer().getTier() == tier)
                .filter(d -> d.getOrderDate().compareTo(startDate) >= 0)
                .filter(d -> d.getOrderDate().compareTo(endDate) >= 0)
                .flatMap(p -> p.getProducts().stream())
                .distinct().collect(Collectors.toList());
    }

    //get most recent placed orders
    public List<Order> getRecentOrders(LocalDate date) {
        return getAllOrder()
                .stream()
                .sorted(Comparator.comparing(Order::getOrderDate))
                .limit(3)
                .collect(Collectors.toList());
    }

    //get Product created on a given date
    public List<Product> getAllOrderByDate(LocalDate localDate) {
        return getAllOrder()
                .stream()
                .filter(d -> d.getOrderDate().isEqual(localDate))
                .peek(o -> System.out.println(o.toString())) //printing the order
                .flatMap(o -> o.getProducts().stream()) //returning the product
                .distinct().toList();
    }

    public Double getPriceOfAllOrderByGivenDate(LocalDate startDate, LocalDate endDate) {
        return orderRepository.findAll()
                .stream()
                .filter(o -> o.getOrderDate().compareTo(startDate) >= 0) //filter the date
                .filter(o -> o.getOrderDate().compareTo(endDate) < 0)
                .flatMap(p -> p.getProducts().stream())//getting the products and mapping to the particular order //one to many mapping
                .mapToDouble(p -> p.getPrice())
                .sum(); //return total amount

    }

    public Double getAveragePriceByParticularDate(LocalDate date) {

        return orderRepository.findAll()
                .stream().filter(o -> o.getOrderDate().equals(date))
                .flatMap(p -> p.getProducts().stream())
                .mapToDouble(pr -> pr.getPrice())
                .average().getAsDouble();
    }

    //get the order id and product in map

    public Map<Long, Set<Product>> getOrderAndProductMap() {
        return orderRepository.findAll()
                .stream().collect(
                        Collectors.toMap(
                                o -> o.getId(),
                                o -> o.getProducts()
                        )
                );
    }

    //get order groupBy customer

    public Map<Customer, List<Order>> getOrderGroupByCustomer() {
        return orderRepository.findAll()
                .stream().collect(
                        Collectors.groupingBy(Order::getCustomer)
                );
    }

    public Map<Order, Double> mapOrderWithProductPrice() {
        return orderRepository.findAll()
                .stream()
                .collect(
                        Collectors.toMap(
                                Function.identity(), //mapping the whole order instead of orderID
                                o -> o.getProducts().stream() //getting the product
                                        .mapToDouble(p -> p.getPrice())//getting the price of product
                                        .sum()
                        )
                );
    }


}
