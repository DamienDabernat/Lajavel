package app;

import app.action.book.ShowBookAction;
import app.controller.IndexController;
import app.responder.book.ShowBookApiResponder;
import app.responder.book.ShowBookHtmlResponder;
import lajavel.Application;
import lajavel.HttpVerb;
import lajavel.Route;

public class MyApp {
    public static void main(String[] args) {
        Application.start(7070);
        Route.register(HttpVerb.GET, "/", IndexController.class, "index");
        Route.register(HttpVerb.GET, "/html/search", ShowBookAction.class, ShowBookHtmlResponder.class);
        Route.register(HttpVerb.GET, "/api/search", ShowBookAction.class, ShowBookApiResponder.class);

    }
}


