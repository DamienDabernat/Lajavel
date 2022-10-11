package app.action.book;

import app.domain.entity.Book;
import app.domain.repository.BookRepository;
import lajavel.Action;
import lajavel.Responder;
import io.javalin.http.Context;

public class ShowBookAction extends Action {

    public ShowBookAction(Responder responder) {
        super(responder);
    }

    @Override
    public void execute(Context context) {
        String isbn = context.req.getParameter("isbn");
        Book book = BookRepository.getOneFromIsbn(isbn);
        this.share(book);
        this.respond(context);
    }
}
