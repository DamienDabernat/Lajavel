package lajavel;

import org.slf4j.Logger;

public final class Log {

    private static Log instance;
    private final Logger logger;

    private Log() {
        this.logger = org.slf4j.LoggerFactory.getLogger("Lajavel");
    }

    public static Log getInstance() {
        if (instance == null) {
            instance = new Log();
        }
        return instance;
    }

    public static void info(String message) {
        getInstance().logger.info(message);
    }

    public static void error(String message) {
        getInstance().logger.error(message);
    }
}