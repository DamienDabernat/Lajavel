package app.responder;

import lajavel.Log;
import lajavel.Responder;
import lajavel.View;
import io.javalin.http.Context;

public class IndexResponder extends Responder {

    public IndexResponder(Context context) {
        super(context);
    }

    @Override
    public void respond() {
        Log.info("coucoucoucoucocucoucou");
        Log.info(View.make("index"));
        this.context.html(View.make("index"));
    }

}
