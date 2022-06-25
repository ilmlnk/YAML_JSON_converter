package converter;
import converter.logger.Logger;
import converter.utils.ConsoleArgsController;
import java.net.URISyntaxException;

public class RunConverter {
    public static Logger logger = new Logger();
    public static void main(String[] args) throws URISyntaxException {
        ConsoleArgsController.sendData(args);
    }
}
