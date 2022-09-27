package app;

import app.action.IndexAction;
import app.action.book.ShowBookAction;
import app.responder.IndexResponder;
import app.responder.book.ShowBookApiResponder;
import app.responder.book.ShowBookHtmlResponder;
import lajavel.Application;
import lajavel.Log;
import lajavel.Route;

public class MyApp {
    public static void main(String[] args) {
        Application.start(7070);
        Route.get("/", IndexAction.class.getName(), IndexResponder.class.getName());
        Route.get("/html/search", ShowBookAction.class.getName(), ShowBookHtmlResponder.class.getName());
        Route.get("/api/search", ShowBookAction.class.getName(), ShowBookApiResponder.class.getName());

        Log.info("Application started");
    }
}


