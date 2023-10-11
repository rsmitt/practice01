package ru.edu;

import ru.edu.dao.BookDaoImpl;
import ru.edu.model.Book;
import ru.edu.service.BookService;


public class App {
    public static void main( String[] args ) {
        BookService service = new BookService(new BookDaoImpl());

        Book book = service.getBookById(1);
        System.out.println(book);

        System.out.println(service.removeFromStock(100));
    }
}
