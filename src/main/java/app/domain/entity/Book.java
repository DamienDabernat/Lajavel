package app.domain.entity;

public class Book {

    public String title;
    public Author author;

    public String isbn;

    public Book(String title, Author author, String isbn) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
    }

    public String getCover() {
        return "https://covers.openlibrary.org/b/isbn/" + this.isbn + "-M.jpg";
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", isbn='" + isbn + '\'' +
                '}';
    }
}
