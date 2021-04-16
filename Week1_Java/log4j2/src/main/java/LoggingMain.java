import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/*
    Logging Level
    ALL => all levels
    DEBUG => designates fine-grained informational events that are most useful to debug an application
    INFO => informational messages that highlight the progress of the application at the coarse grained level
    WARN => designates potentially harmful situations
    ERROR => designates error events that might still allow the application to continue running
    FATAL => severe error events that presumably lead the application to abort
    OFF => highest possible level, intended to turn off logging
 */

public class LoggingMain {
    public static final Logger logger = LogManager.getLogger(LoggingMain.class.getName());
    public static final Logger rootLogger = LogManager.getRootLogger();
    public static void main(String[] args) {
        boolean authenticated = false;
        String action = "withdraw";
        logger.atError().log("Couldn't perform action '{}' because authentication status is: '{}'", action.toUpperCase(), (""+authenticated).toUpperCase());
        rootLogger.debug("Won't show up because the logging level for root is set to higher than debug");
        rootLogger.info("rootLogger can log at 'info' and higher because its logging level is: {}", rootLogger.getLevel());
    }
}
