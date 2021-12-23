package com.kang.springbootbook;

import com.kang.springbootbook.entity.Book;
import com.kang.springbootbook.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringbootBookApplicationTests {

    @Autowired
    private BookRepository bookRepository;

    @Test
    void contextLoads() {
    }

    @Test
    void save() {
        Book book = new Book();
        book.setName("spring boot");
        book.setAuthor("zhangsan");
        Book book1 = bookRepository.save(book);
        System.out.println(book1);
    }

    @Test
    void findById() {
        System.out.println(bookRepository.findById(1).get());
    }

    @Test
    void update() {
        Book book = new Book();
        book.setId(127);
        book.setName("hyk");
        Book book1 = bookRepository.save(book);
        System.out.println(book1);
    }

    @Test
    void delete() {
        bookRepository.deleteById(130);
    }
}
