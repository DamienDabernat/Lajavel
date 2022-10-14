package app.action.book;

import app.domain.entity.Book;
import app.domain.repository.BookRepository;
import lajavel.Action;
import lajavel.Responder;
import lajavel.Response;

public class ShowBookAction extends Action {

    public ShowBookAction(Responder responder) {
        super(responder);
    }

    @Override
    public void execute(Response response) {
        String isbn = response.request.getParameter("isbn");
        Book book = BookRepository.getOneFromIsbn(isbn);
        this.share(book);
        this.respond(response);
    }
}
