package converter.conversion;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import converter.utils.FileController;

import java.io.File;
import java.io.IOException;

import static converter.RunConverter.logger;

public class ConvertToYaml implements converter.conversion.Conversion {
    @Override
    public boolean conversion(File file) {
        double startTime = System.currentTimeMillis();
        try {
            ObjectMapper jsonReader = new ObjectMapper(new JsonFactory());
            Object obj = jsonReader.readValue(file, Object.class);
            ObjectMapper yamlWriter = new ObjectMapper(new YAMLFactory());
            File newYamlFile = new File(FileController.getFilePathWithoutExtension(file) + ".yaml");
            double executionTime = System.currentTimeMillis() - startTime;
            yamlWriter.writeValue(newYamlFile, obj);

            logger.info(file, newYamlFile);
            logger.info(executionTime);

            return true;

        } catch (IOException e) {
            System.err.println("It is not possible to perform operation with files!");
            logger.error(file.getName() + " was not converted from JSON to YAML", e);
            return false;
        }
    }
}
