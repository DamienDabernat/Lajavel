package app;

import app.action.book.ShowBookAction;
import app.controller.IndexController;
import app.domain.entity.Author;
import app.domain.entity.Book;
import app.responder.book.ShowBookApiResponder;
import app.responder.book.ShowBookHtmlResponder;
import lajavel.*;

public class MyApp {
    public static void main(String[] args) {
        Application.start(7070, Application.Mode.DEVELOPMENT);

        // Register route through MVC pattern
        Route.register(HttpVerb.GET, "/", IndexController.class, "index");
        // Register route through ADR pattern
        Route.register(HttpVerb.GET, "/html/search", ShowBookAction.class, ShowBookHtmlResponder.class);
        Route.register(HttpVerb.GET, "/api/search", ShowBookAction.class, ShowBookApiResponder.class);

    }

}


