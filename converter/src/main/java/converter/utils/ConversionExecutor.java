package converter.utils;

import converter.conversion.ConvertToJSON;
import converter.conversion.ConvertToYaml;
import java.io.File;
import java.io.IOException;
import java.util.Set;

import static converter.RunConverter.logger;

public class ConversionExecutor {
    public static void executeConversion(Set<File> filesSet) {
        for (File file : filesSet) {
            if (FileController.isYamlFile(file)) {
                System.out.println(new ConvertToJSON().conversion(file) ? "Successful conversion from Yaml to JSON."
                        : "Conversion from Yaml to JSON was failed.");
            } else if (FileController.isJsonFile(file)) {
                System.out.println(new ConvertToYaml().conversion(file) ? "Successful conversion from JSON to Yaml."
                        : "Conversion from JSON to Yaml was failed.");
            } else {
                System.err.println(file.toPath().getFileName() + " is not valid!");
                logger.error(file.toPath().getFileName() + " is not valid!", new IOException());
            }
        }
    }
}
