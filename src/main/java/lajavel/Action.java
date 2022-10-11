package lajavel;

import io.javalin.http.Context;

public abstract class Action {

    private Responder responder;

    protected void respond(Context context) {
        this.responder.respond(context);
    }

    protected void share(Object object) {
        this.responder.share(object);
    }

    public Action(Responder responder) {
        this.responder = responder;
    }

    public abstract void execute(Context context);
}
