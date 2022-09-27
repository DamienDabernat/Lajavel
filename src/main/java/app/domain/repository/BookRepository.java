package app.domain.repository;

import app.domain.entity.Author;
import app.domain.entity.Book;

import java.util.List;

public class BookRepository {

    private static BookRepository instance;
    private List<Book> data;

    private BookRepository() {
        Book book = new Book("The Hitchhiker's Guide to the Galaxy", new Author("Douglas", "Adams"), "2207261883");
        this.data = List.of(book);
    }

    public static BookRepository getInstance() {
        if (instance == null) {
            instance = new BookRepository();
        }
        return instance;
    }

    public static Book getOneBook() {
        return BookRepository.getInstance().data.get(0);
    }

    public static Book getOneFromIsbn(String isbn) {
        return BookRepository.getInstance().data.stream()
                .filter(book -> book.isbn.equals(isbn))
                .findFirst()
                .orElse(null);
    }

}
