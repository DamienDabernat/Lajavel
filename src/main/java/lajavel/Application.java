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

    public static void start(int port) {
        if (instance == null) {
            instance = new Application(port);
            Log.info("Application started");
        } else {
            throw new RuntimeException("Application already started");
        }
    }

    public static Application getInstance() {
        if (instance == null) {
            throw new RuntimeException("Application not started");
        }
        return instance;
    }
}