package app.responder.book;

import app.domain.entity.Book;
import lajavel.Responder;
import io.javalin.http.Context;

public class ShowBookApiResponder extends Responder {

    @Override
    public void respond(Context context) {
        Book book = this.call(Book.class);
        context.result(book.toString());
    }
}
