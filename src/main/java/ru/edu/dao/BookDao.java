package ru.edu.dao;

import ru.edu.model.Book;

import java.util.List;

public interface BookDao {

    Book getBookById(int id);
    List<Book> getAll();
    void add(Book book);
    Book deleteById(int id);

}
