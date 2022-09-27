package app.action;

import lajavel.Action;
import lajavel.Responder;
import io.javalin.http.Context;

public class IndexAction extends Action {

    public IndexAction(Responder responder, Context context) {
        super(responder, context);
    }

    @Override
    public void execute(Context context) {
        this.responder.respond();
    }
}
