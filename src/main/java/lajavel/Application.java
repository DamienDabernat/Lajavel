package lajavel;

import io.javalin.Javalin;

public final class Application {

    public enum Mode {
        DEVELOPMENT(3),
        TEST(1),
        PRODUCTION(0);

        public final int level;

        Mode(int level) {
            this.level = level;
        }
    }

    private static Application instance;
    private final int port;
    public final Mode mode;

    public Javalin server;

    private Application(int port, Mode mode) {
        this.port = port;
        this.server = Javalin.create().start(this.port);
        this.mode = mode;
    }

    /**
     * Start the application
     * @param port The port to listen
     */
    public static void start(int port) {
        start(port, Mode.DEVELOPMENT);
    }

    /**
     * Start the application
     * @param port The port to listen
     * @param mode The mode of the application (DEVELOPMENT, TEST, PRODUCTION)
     */
    public static void start(int port, Mode mode) {
        if (instance == null) {
            instance = new Application(port, mode);
            Log.info("Application started");
        } else {
            throw new RuntimeException("Application already started");
        }
    }

    /**
     * Get the instance of the application
     * @return The instance of the application
     */
    public static Application getInstance() {
        if (instance == null) {
            throw new RuntimeException("Application not started");
        }
        return instance;
    }
}