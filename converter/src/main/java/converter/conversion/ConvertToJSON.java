package converter.conversion;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import converter.utils.FileController;
import static converter.RunConverter.logger;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class ConvertToJSON implements converter.conversion.Conversion {
    @Override
    public boolean conversion(File file) {
        double startTime = System.currentTimeMillis();
        try {
            String fileContent = new String(Files.readAllBytes(file.toPath()));
            ObjectMapper yamlReader = new ObjectMapper(new YAMLFactory());
            Object obj = yamlReader.readValue(fileContent, Object.class);
            ObjectMapper jsonWriter = new ObjectMapper();
            double executionTime = System.currentTimeMillis() - startTime;
            File newJsonFile = new File(FileController.getFilePathWithoutExtension(file) + ".json");
            jsonWriter.writeValue(newJsonFile, obj);

            logger.info(file, newJsonFile);
            logger.info(executionTime);

            return true;

        } catch (IOException e) {
            System.err.println("It is not possible to perform operation with files!");
            logger.error(file.getName() + " was not converted from YAML to JSON!", e);
            return false;
        }
    }
}
