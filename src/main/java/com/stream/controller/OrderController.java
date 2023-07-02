package com.stream.controller;

import com.stream.model.Customer;
import com.stream.model.Order;
import com.stream.model.Product;
import com.stream.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public Order createOrder(@RequestBody Order order) {
        return orderService.createOrder(order);
    }

    @GetMapping
    public List<Order> findAllOrder() {
        return orderService.getAllOrder();
    }

    @GetMapping("{/id}")
    public Order findOrderById(@PathVariable Long id) {
        return orderService.getOrderById(id);
    }

    @GetMapping("/orderByCategory")
    public List<Order> findOrderByCategory(@RequestParam String productCategory) {
        return orderService.findOrderByProductCategory(productCategory);
    }


    @GetMapping("/orderByDate")
    public List<Product> getProductByOrder(@RequestParam LocalDate startDate, @RequestParam LocalDate endDate, @RequestParam int tier) {
        return orderService.findOrderBasedOnGivenDate(startDate, endDate, tier);
    }

    @GetMapping("/recentOrders")
    public List<Order> getRecentOrders(@RequestParam LocalDate startDate) {
        return orderService.getRecentOrders(startDate);
    }

    @GetMapping("/productOrderByDate")
    public List<Product> getOrderByDate(@RequestParam LocalDate startDate) {
        return orderService.getAllOrderByDate(startDate);
    }


    @GetMapping("/getTotalPrice")
    public Double getPriceOfAllOrderByGivenDate(@RequestParam LocalDate startDate, @RequestParam LocalDate endDate) {
        return orderService.getPriceOfAllOrderByGivenDate(startDate, endDate);
    }

    @GetMapping("/getAveragePrice")
    public Double getAveragePriceByParticularDate(@RequestParam LocalDate date) {
        return orderService.getAveragePriceByParticularDate(date);
    }

    @GetMapping("/getOrderProductRelation")
    public Map<Long, Set<Product>> getOrderAndProductMap() {
        return orderService.getOrderAndProductMap();
    }

    @GetMapping("/getOrderGroupByCustomer")
    public Map<Customer, List<Order>> getOrderGroupByCustomer() {
        return orderService.getOrderGroupByCustomer();
    }
    @GetMapping("/mapOrderWithProductPrice")
    public Map<Order, Double> mapOrderWithProductPrice(){
        return orderService.mapOrderWithProductPrice();
    }
}
