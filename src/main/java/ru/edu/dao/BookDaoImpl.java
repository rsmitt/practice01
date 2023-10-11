package ru.edu.dao;

import ru.edu.exceptions.ItemNotFoundException;
import ru.edu.model.Book;

import java.util.Arrays;
import java.util.List;

public class BookDaoImpl implements BookDao{

    private final List<Book> books;

    public BookDaoImpl() {
        books = Arrays.asList(
                new Book(1, "Book1", 10.0),
                new Book(2, "Book2", 15.0),
                new Book(3, "Book3", 10.0),
                new Book(4, "Book4", 25.0),
                new Book(5, "Book5", 30.0)
                );
    }

    @Override
    public Book getBookById(int id) {
        try {
            System.out.println("Executing getBookById method...");
            return books.get(id);
        } catch (IndexOutOfBoundsException e) {
            throw new ItemNotFoundException("Book not found, id = " + id);
        }
    }

    @Override
    public List<Book> getAll() {
        return books;
    }

    @Override
    public void add(Book book) {
        books.add(book);
    }

    @Override
    public Book deleteById(int id) {
        getBookById(id);
        return books.remove(id);
    }
}
