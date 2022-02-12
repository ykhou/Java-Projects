package com.kang.springbootbook.service.impl;

import com.kang.springbootbook.entity.Book;
import com.kang.springbootbook.repository.BookRepository;
import com.kang.springbootbook.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;
    @Override
    public Page<Book> findAll(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page-1, size);
        return bookRepository.findAll(pageable);
    }

    @Override
    public Book findById(Integer id) {
        return bookRepository.findById(id).get();
    }

    @Override
    public String save(Book book) {
        if (bookRepository.save(book) != null) {
            return "success";
        } else {
            return "error";
        }
    }

    @Override
    public String update(Book book) {
        if (bookRepository.save(book) != null) {
            return "success";
        } else {
            return "error";
        }
    }

    @Override
    public void delete(Integer id) {
        bookRepository.deleteById(id);
    }
}
