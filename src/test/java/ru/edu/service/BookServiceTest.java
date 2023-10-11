package ru.edu.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.edu.dao.BookDaoImpl;
import ru.edu.exceptions.DiscountException;
import ru.edu.model.Book;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {

    @Mock
    private BookDaoImpl daoImpl;

    @InjectMocks
    private BookService service;

    @BeforeEach
    void setUp() {
        //MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @DisplayName("check getBookId old style")
    void getBookByIdOldStyle() {
        BookDaoImpl daoImplMock = Mockito.mock(BookDaoImpl.class);
        BookService service = new BookService(daoImplMock);

        Book book = new Book(1, "test book1", 1.0);
        when(daoImplMock.getBookById(anyInt())).thenReturn(book);

        Assertions.assertThat(service.getBookById(1).getName()).isEqualTo("test book1");
    }

    @Test
    void getBookById() {
        Book book = new Book(1, "test book1", 1.0);
        when(daoImpl.getBookById(anyInt())).thenReturn(book);

        Assertions.assertThat(service.getBookById(1).getName()).isEqualTo("test book1");
    }

    @Test
    void getAllBooksInStock() {
        List<Book> books = new ArrayList<>(2);
        books.add(new Book(1, "test book1", 1.0));
        books.add(new Book(2, "test book2", 2.0));

        when(daoImpl.getAll()).thenReturn(books);

        Assertions.assertThat(service.getAllBooksInStock().size()).isEqualTo(2);
    }

    @Test
    void addToStock() {
        Book book = new Book(1, "test book1", 1.0);

        doNothing().when(daoImpl).add(any(Book.class));
        service.addToStock(book);

        verify(daoImpl, times(1)).add(book);
    }

    @Test
    void removeFromStock() {
        Book book = new Book(1, "test book1", 1.0);
        when(daoImpl.deleteById(anyInt())).thenReturn(book);

        Assertions.assertThat(service.removeFromStock(1).getName()).isEqualTo("test book1");
    }

    @Test
    void getPriceWithDiscount() {
        Book book = new Book(1, "test book1", 100.0);
        when(daoImpl.getBookById(anyInt())).thenReturn(book);

        Assertions.assertThat(service.getPriceWithDiscount(1, 10)).isEqualTo(90.0);
    }

    @Test
    void getPriceWithDiscountException() {
        Assertions.assertThatThrownBy(() -> service.getPriceWithDiscount(1, 0))
                .isInstanceOf(DiscountException.class)
                .hasMessage("Discount cannot be less or equal 0");
    }
}