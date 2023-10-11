package ru.edu.service;

import ru.edu.dao.BookDao;
import ru.edu.exceptions.DiscountException;
import ru.edu.model.Book;

import java.util.List;

public class BookService {

    private final BookDao dao;

    public BookService(BookDao dao) {
        this.dao = dao;
    }

    public Book getBookById(int id) {
        return dao.getBookById(id);
    }

    public List<Book> getAllBooksInStock() {
        return dao.getAll();
    }

    public void addToStock(Book book) {
        dao.add(book);
    }

    public Book removeFromStock(int bookId) {
        return dao.deleteById(bookId);
    }

    /**
     *
     * @param bookId
     * @param discount
     * @return
     */
    public double getPriceWithDiscount(int bookId, double discount) {
        if (discount <= 0) {
            throw new DiscountException("Discount cannot be less or equal 0");
        }

        Book book = getBookById(bookId);
        return book.getPrice() - book.getPrice() * discount / 100;
    }
}
