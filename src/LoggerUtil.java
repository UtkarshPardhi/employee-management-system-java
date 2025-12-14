import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoggerUtil {

    public static Logger getLogger(Class<?> cls) {
        Logger logger = Logger.getLogger(cls.getName());
        logger.setLevel(Level.ALL);

        ConsoleHandler handler = new ConsoleHandler();
        handler.setLevel(Level.ALL);

        logger.addHandler(handler);
        logger.setUseParentHandlers(false);

        return logger;
    }
}
