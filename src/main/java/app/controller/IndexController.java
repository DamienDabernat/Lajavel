package app.controller;

import io.javalin.http.Context;
import lajavel.Controller;
import lajavel.View;

public class IndexController extends Controller {
    public void index(Context context) {
        context.html(View.make("index"));
    }
}
