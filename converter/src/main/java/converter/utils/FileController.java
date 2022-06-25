package converter.utils;

import converter.conversion.Conversion;

import java.io.File;
import java.net.URISyntaxException;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileController {

    public static boolean isJsonFile(File file) {
        return getFileExtension(file).equals("json");
    }

    public static boolean isYamlFile(File file) {
        return getFileExtension(file).equals("yaml");
    }

    public static String getFilePathWithoutExtension(File file) {
        return getOutputFolder(file) + File.separator + getFileName(file);
    }

    public static String getFileExtension(File file) {
        StringBuilder stringBuilder = new StringBuilder(file.getName()).reverse();
        return new StringBuilder(stringBuilder.substring(0, stringBuilder.indexOf("."))).reverse().toString();
    }

    public static String getFileName(File file) {
        return file.getName().substring(0, file.getName().indexOf(getFileExtension(file)) - 1);
    }

    public static String getFileFullName(File file) {
        return file.getName();
    }

    public static String getOutputFolder(File file) {
        File newFile = new File(file.getParent() + File.separator + "converted");
        newFile.mkdir();
        return newFile.getAbsolutePath();
    }

    public static Set<File> getFilesSet(String dir) {
        return Stream.of(Objects.requireNonNull(new File(dir).listFiles()))
                .filter(file -> !file.isDirectory())
                .collect(Collectors.toSet());
    }

    public static String getPathToDefaultFolder() throws URISyntaxException {
        return new File((Conversion.class.getProtectionDomain()
                .getCodeSource().getLocation().toURI()).getPath()).getParent();
    }

}
