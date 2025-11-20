import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogDemo
{
    static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(LogDemo.class);


        logger.info("this is info");
        logger.warn("this is a warning!!");
        logger.error("This is an error!!!!");
    }
}
