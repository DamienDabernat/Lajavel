package lajavel;

public abstract class Action {

    private Responder responder;

    protected void respond(Response response) {
        this.responder.respond(response);
    }

    protected void share(Object object) {
        this.responder.share(object);
    }

    public Action(Responder responder) {
        this.responder = responder;
    }

    public abstract void execute(Response response);
}
