package app.responder.book;

import app.domain.entity.Book;
import lajavel.Responder;
import io.javalin.http.Context;
import lajavel.Response;

public class ShowBookApiResponder extends Responder {

    @Override
    public void respond(Response response) {
        Book book = this.fetch(Book.class);
        response.json(book);
    }
}
