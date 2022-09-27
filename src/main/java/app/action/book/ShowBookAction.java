package app.action.book;

import app.domain.entity.Book;
import app.domain.repository.BookRepository;
import lajavel.Action;
import lajavel.Responder;
import io.javalin.http.Context;

public class ShowBookAction extends Action {

    public ShowBookAction(Responder responder, Context context) {
        super(responder, context);
    }

    @Override
    public void execute(Context context) {
        String isbn = context.req.getParameter("isbn");
        Book book = BookRepository.getOneFromIsbn(isbn);
        this.responder.define(book);
        this.responder.respond();
    }
}
