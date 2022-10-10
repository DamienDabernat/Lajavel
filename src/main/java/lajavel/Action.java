package lajavel;

import io.javalin.http.Context;

public abstract class Action {

    public abstract void execute(Context context, Responder responder);

}
