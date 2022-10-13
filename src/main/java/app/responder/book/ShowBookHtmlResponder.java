package app.responder.book;

import app.domain.entity.Author;
import app.domain.entity.Book;
import kotlin.Pair;
import lajavel.Responder;
import lajavel.View;
import io.javalin.http.Context;

import java.util.Map;

public class ShowBookHtmlResponder extends Responder {

    @Override
    public void respond(Context context) {
        Book book = this.fetch(Book.class);
        context.header("Content-Type", "text/html");

        context.html(
                View.make("show",
                        Map.entry("book", book),
                        Map.entry("author", book.author))
        );
    }
}
