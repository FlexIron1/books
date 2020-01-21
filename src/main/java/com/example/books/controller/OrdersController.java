package com.example.books.controller;

import com.example.books.entity.Author;
import com.example.books.entity.Orders;
import com.example.books.repository.OrdersRepository;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/orders")
public class OrdersController {
    @Autowired
    private OrdersRepository ordersRepository;

    @ApiOperation( value  =  "Посмотреть список всех заказов",response = Iterable.class)
    @GetMapping("/all")
    public Iterable<Orders> allOrders() {
        return ordersRepository.findAll();
    }
    @ApiOperation( value  =  "Добавить новый заказ",response = Orders.class)
    @PostMapping("/add")
    public Orders addOrders(@RequestBody Orders orders) {

        return ordersRepository.save(orders);
    }
    @ApiOperation( value  =  "Найти заказ по уникальному ключу",response = Optional.class)
    @GetMapping("/{id}")
    public Optional<Orders> ordersById(@PathVariable("id") long id) {
        return ordersRepository.findById(id);
    }
    @ApiOperation( value  =  "Обновить информацию о заказе",response = Orders.class)
    @PutMapping("/{id}")
    public Orders updateAuthors(@RequestBody Orders orders,@PathVariable("id")long id) {
       orders.setId(id);
        return ordersRepository.save(orders);
    }
    // delete course from database
    @ApiOperation( value  =  "Отменить заказ по уникальному ключу",response = void.class)
    @DeleteMapping("/{id}")
    public void deleteOrders(@PathVariable("id") long id) {
        ordersRepository.deleteById(id);
    }
}
