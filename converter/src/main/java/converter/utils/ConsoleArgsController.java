package converter.utils;

import java.net.URISyntaxException;

public class ConsoleArgsController {
    public static void sendData(String... args) throws URISyntaxException {
        String path;
        if (args.length != 0) path = args[0];
        else path = FileController.getPathToDefaultFolder();
        ConversionExecutor.executeConversion(FileController.getFilesSet(path));
    }
}
