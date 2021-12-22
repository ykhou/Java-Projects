package com.kang.springbootbook.repository;

import com.kang.springbootbook.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Integer> {

}
