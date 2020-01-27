package com.example.books.controller;


import com.example.books.entity.Author;
import com.example.books.entity.Books;
import com.example.books.repository.AuthorRepository;
import com.example.books.repository.BooksRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path = "/books")
@Api(value = "Книги",description = "Книги")
public class BooksController {

    @Autowired
    private BooksRepository booksRepository;
    private AuthorRepository authorRepository;
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
    @ApiOperation(value = "Добавить книги", response = Books.class)

    @PostMapping("/add")
    public Books addBooks(@RequestBody Books books) {
        return booksRepository.save(books);
    }

    @ApiOperation(value = "Посмотреть все книги и их авторов", response = Iterable.class)
    @GetMapping("/all")
    public Iterable<Books> allBooks() {
        return booksRepository.findAll();
    }

    @ApiOperation(value = "Найти какую либо книгу по id", response = Optional.class)
    @GetMapping("/{id}")
    public Optional<Books> booksById(@PathVariable("id") long id) {
        return booksRepository.findById(id);
    }

    @ApiOperation(value = "Найти автора определенной книги,по айди книги",
            response = Optional.class)
    @GetMapping("/{id}/getAuthors")
    public Optional<Books> getBooksAuthor(@PathVariable long id) {
        return booksRepository.findById(id);
    }

    @ApiOperation(value = "Обновить книгу", response = Books.class)
    @PutMapping("/update")
    public Books updateBooks(@RequestBody Books books) {

        return booksRepository.save(books);
    }
    @ApiOperation(value = "Удалить книгу", response = void.class)
    @DeleteMapping("/{id}")
    public void deleteBooks(@PathVariable("id") long id) {

        booksRepository.deleteById(id);
    }
}
