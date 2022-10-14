package app.responder.book;

import app.domain.entity.Book;
import lajavel.Responder;
import lajavel.Response;
import lajavel.View;
import io.javalin.http.Context;

import java.util.Map;

public class ShowBookHtmlResponder extends Responder {

    @Override
    public void respond(Response response) {
        Book book = this.fetch(Book.class);
        response.header("Content-Type", "text/html");

        response.html(
                View.make("show",
                        Map.entry("book", book),
                        Map.entry("author", book.author))
        );
    }
}
