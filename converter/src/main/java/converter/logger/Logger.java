package converter.logger;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import static converter.utils.FileController.getFileExtension;
import static converter.utils.FileController.getPathToDefaultFolder;

public class Logger {
    private File logFile;
    private final String DELIMITER = "-".repeat(5);
    private final String DATE_PATTERN = "dd-MM-yyyy HH:mm:ss";
    private final String ARROW = "-->";
    private final String LOG_FILE_NAME = "result.log";

    public Logger() {
        try {
            this.logFile = new File(getPathToDefaultFolder() + File.separator + LOG_FILE_NAME);
        } catch (URISyntaxException e) {
            System.err.println("It is not possible to find a file!");
        }
    }

    public void info(File file, File newFile){
        try {
            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat formatter = new SimpleDateFormat(DATE_PATTERN);

            PrintWriter printWriter = new PrintWriter(new FileWriter(logFile, true));
            printWriter.println(DELIMITER + formatter.format(calendar.getTime()) + DELIMITER);
            printWriter.println(getFileExtension(file).toUpperCase() + " file name : " +
                    file.getName() + " " + ARROW + " " + newFile.getName());
            printWriter.println(getFileExtension(file).toUpperCase() + " file size : " +
                    Files.size(file.toPath()) + " " + ARROW + " " + Files.size(newFile.toPath()));
            printWriter.close();
        } catch (IOException e) {
            System.err.println("It is not possible to write info in logfile.");
        }
    }

    public void info(double info) {
        try {
            PrintWriter printWriter = new PrintWriter(new FileWriter(logFile, true));
            printWriter.println("Execution time: " + info + "ms\n");
            printWriter.close();
        } catch (IOException e) {
            System.err.println("It is not possible to write info in logfile.");
        }
    }

    public void error(String info, Exception exception) {
        try {
            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat formatter = new SimpleDateFormat(DATE_PATTERN);

            PrintWriter printWriter = new PrintWriter(new FileWriter(logFile, true));
            printWriter.println(DELIMITER + " " + formatter.format(calendar.getTime()) + " " + DELIMITER);
            printWriter.println(info + " " + exception.toString() + "\n");
            printWriter.close();
        } catch (IOException e) {
            System.err.println("It is not possible to write info in logfile.");
        }
    }
}
