package lajavel;

import io.javalin.Javalin;

public final class Application {
    private static Application instance;
    private final int port;

    public Javalin server;

    private Application(int port) {
        this.port = port;
        this.server = Javalin.create().start(this.port);
    }

    public static Application start(int port) {
        if (instance == null) {
            instance = new Application(port);
        }
        return instance;
    }

    public static Application getInstance() {
        if (instance == null) {
            throw new RuntimeException("Application not started");
        }
        return instance;
    }
}