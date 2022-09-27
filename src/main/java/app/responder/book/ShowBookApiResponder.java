package app.responder.book;

import app.domain.entity.Book;
import lajavel.Responder;
import io.javalin.http.Context;

public class ShowBookApiResponder extends Responder {

    public ShowBookApiResponder(Context context) {
        super(context);
    }

    @Override
    public void respond() {
        Book book = this.call(Book.class);
        this.context.result(book.toString());
    }
}
