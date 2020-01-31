package com.example.books.controller;

import com.example.books.entity.Orders;
import com.example.books.repository.OrdersRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.elasticsearch.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Optional;

@RestController
@RequestMapping("/orders")
@Api(value = "Заказы ", description = "Заказы клиентов")
public class OrdersController {

    @Autowired
    private OrdersRepository ordersRepository;

    @ApiResponses(value = {
            @ApiResponse(code = 200,
                    message = "Список успешно найден"),
            @ApiResponse(code = 401,
                    message = "Вы не авторизованы для просмотра ресурса"),
            @ApiResponse(code = 403,
                    message = "Доступ к ресурсу, который вы пытались получить, запрещен"),
            @ApiResponse(code = 404,
                    message = "Ресурс, который вы пытались получить, не найден")
    })

    @ApiOperation(value = "Посмотреть список всех заказов", response = Iterable.class)
    @GetMapping("/all")
    public Iterable<Orders> allOrders() {
        return ordersRepository.findAll();
    }

    @ApiOperation(value = "Добавить новый заказ", response = Orders.class)
    @PostMapping("/add")
    public Orders addOrders(@RequestBody Orders orders) {
        orders.setOrderExecutionFlag(false);
        orders.setOrder_Execution_Date(null);

        return ordersRepository.save(orders);
    }

    @ApiOperation(value = "Найти заказ по уникальному ключу", response = Optional.class)
    @GetMapping("/{id}")
    public Optional<Orders> ordersById(@PathVariable("id") long id) {
        return ordersRepository.findById(id);
    }


    @ApiOperation(value = "Отменить заказ по уникальному ключу", response = void.class)
    @DeleteMapping("/{id}")
    public void deleteOrders(@PathVariable("id") long id) {
        ordersRepository.deleteById(id);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Выполнить заказ", response = void.class)
    public Orders executeOrder(@RequestBody Orders orders,@PathVariable("id") long id) throws ResourceNotFoundException {
        orders.setId(id);
        orders.setOrderExecutionFlag(false);
        orders.setOrder_Execution_Date(new Date());
      return ordersRepository.save(orders);
    }
}
