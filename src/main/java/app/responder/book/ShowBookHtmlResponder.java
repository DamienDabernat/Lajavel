package app.responder.book;

import app.domain.entity.Book;
import lajavel.Responder;
import lajavel.View;
import io.javalin.http.Context;

public class ShowBookHtmlResponder extends Responder {

    @Override
    public void respond(Context context) {
        Book book = this.fetch(Book.class);
        context.header("Content-Type", "text/html");
        context.html(View.make("show", book, book.author));
    }
}
