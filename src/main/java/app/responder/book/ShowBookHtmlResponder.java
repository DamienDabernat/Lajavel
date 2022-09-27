package app.responder.book;

import app.domain.entity.Book;
import lajavel.Responder;
import lajavel.View;
import io.javalin.http.Context;

public class ShowBookHtmlResponder extends Responder {

    public ShowBookHtmlResponder(Context context) {
        super(context);
    }

    @Override
    public void respond() {
        Book book = this.call(Book.class);
        this.context.header("Content-Type", "text/html");
        this.context.html(View.make(book, "show"));
    }
}
