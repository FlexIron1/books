package com.example.books.controller;


import com.example.books.model.Client;
import com.example.books.repository.ClientRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/client")
@Api(value = "Клиенты ", description = "Клиенты")
public class ClientController {

    @Autowired
    private ClientRepository clientRepository;
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
    @ApiOperation(value = "Добавить нового клиента", response = Client.class)
    @PostMapping("/add")
    public Client addClient(@RequestBody Client client) {
        return clientRepository.save(client);
    }

    @ApiOperation(value = "Посмотреть всех клиентов", response = Iterable.class)
    @GetMapping("/all")
    public Iterable<Client> allClients() {
        return clientRepository.findAll();
    }

    @ApiOperation(value = "Найти клиента по уникальному ключу", response = Optional.class)
    @GetMapping("/{id}")
    public Optional<Client> clientsById(@PathVariable("id") long id) {
        return clientRepository.findById(id);
    }

    @ApiOperation(value = "Обновить информацию о клиенте", response = Client.class)
    @PutMapping("/{id}")
    public Client updateClients(@RequestBody Client client, @PathVariable Long id) {
        client.setId(id);
        return clientRepository.save(client);
    }

    @ApiOperation(value = "Отменить заказ клиента по id", response = void.class)
    @DeleteMapping("/{id}")
    public void deleteClients(@PathVariable("id") long id) {
        clientRepository.deleteById(id);
    }
}
