package com.kang.springbootbook.controller;

import com.kang.springbootbook.entity.Book;
import com.kang.springbootbook.repository.BookRepository;
import com.kang.springbootbook.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookHandler {
    @Autowired
    private BookService bookService;

    @GetMapping("findAll/{page}/{size}")
    public Page<Book> findAll(@PathVariable("page") Integer page, @PathVariable("size") Integer size) {
        return bookService.findAll(page, size);
    }

    @PostMapping("/save")
    public String save(@RequestBody Book book) {
        return bookService.save(book);
    }

    @GetMapping("/findById/{id}")
    public Book findById(@PathVariable Integer id) {
        return bookService.findById(id);
    }

    @PutMapping("/update")
    public String update(@RequestBody Book book) {
        return bookService.save(book);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Integer id) {
        bookService.delete(id);
    }
}
