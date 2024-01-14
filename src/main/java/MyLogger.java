import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MyLogger{
    private static final Logger logger = LogManager.getLogger(MyLogger.class);

    public static void main(String []args){
        logger.debug("This is debug message.");
        logger.info("This is info message");
        logger.warn("this is warn message");
        logger.error("this is error message.");
    }
}
