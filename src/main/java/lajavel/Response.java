package lajavel;

import io.javalin.http.Context;
import jakarta.servlet.http.HttpServletRequest;


public class Response {

    private final Context context;
    public final HttpServletRequest request;

    public Response(Context context) {
        this.context = context;
        this.request = context.req();
    }

    public void html(String html) {
        this.context.html(html);
    }

    public void json(Object object) {
        this.context.json(object);
    }

    public void redirect(String url) {
        this.context.redirect(url);
    }

    public void status(int status) {
        this.context.status(status);
    }

    public void raw(String value) {
        this.context.result(value);
    }

    public void header(String name, String value) {
        this.context.header(name, value);
    }

}
