package app.action.book;

import app.domain.entity.Book;
import app.domain.repository.BookRepository;
import lajavel.Action;
import lajavel.Responder;
import io.javalin.http.Context;

public class ShowBookAction extends Action {

    @Override
    public void execute(Context context, Responder responder) {
        String isbn = context.req.getParameter("isbn");
        Book book = BookRepository.getOneFromIsbn(isbn);
        responder.define(book);
        responder.respond(context);
    }
}
