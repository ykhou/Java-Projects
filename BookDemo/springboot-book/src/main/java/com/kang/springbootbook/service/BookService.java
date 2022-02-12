package com.kang.springbootbook.service;

import com.kang.springbootbook.entity.Book;
import org.springframework.data.domain.Page;

public interface BookService {
    public Page<Book> findAll(Integer page, Integer size);

    public Book findById(Integer id);

    public String save(Book book);

    public String update(Book book);

    public void delete(Integer id);
}
