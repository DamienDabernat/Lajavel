package lajavel;

import io.javalin.http.Context;

public abstract class Action {

    protected Responder responder;

    public Action(Responder responder, Context context) {
        this.responder = responder;
    }

    public abstract void execute(Context context);

}
