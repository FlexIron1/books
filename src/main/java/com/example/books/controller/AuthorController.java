package com.example.books.controller;


import com.example.books.entity.Author;
import com.example.books.repository.AuthorRepository;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/authors")

@Api(value="Авторы книг", description="Авторы определенных книг")

public class AuthorController {
    @Autowired
    AuthorRepository authorRepository;
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
    @ApiOperation( value  =  "Добавить нового автора , новой книги",response = Author.class)
    @PostMapping("/add")
    private Author addAuthors(@RequestBody Author author) {

        return authorRepository.save(author);
    }
    @ApiOperation( value  =  "Посмотреть всех авторов и их книги",response = Iterable.class)
    @GetMapping("/all")
    public Iterable<Author> allAuthors() {
        return authorRepository.findAll();
    }
    @ApiOperation( value  =  "Найти автора и его книги по id",response = Optional.class)
    @GetMapping("/{id}")
    public Optional<Author> authorsById(@PathVariable("id") long id) {
        return authorRepository.findById(id);
    }
    @ApiOperation( value  =  "Обновить автора...знаю глупо))0",response = Author.class)
    @PutMapping("/update")
    public Author updateAuthors(@RequestBody Author author) {
        return authorRepository.save(author);
    }
    // delete course from database
    @ApiOperation( value  =  "Удалить автора,по id",response = void.class)
    @DeleteMapping("/{id}")
    public void deleteAuthors(@PathVariable("id") long id) {
        authorRepository.deleteById(id);
    }


}


