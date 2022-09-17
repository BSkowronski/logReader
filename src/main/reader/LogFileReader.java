package main.reader;

import main.exceptions.DirectoryNotFoundException;
import java.io.File;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class LogFileReader implements Reader {
    public List<File> readFilesFromDirectory(String directory) {
        File file = new File(directory);
        if (!file.exists()) {
            throw new DirectoryNotFoundException("Directory not found");
        } else {
            return sortFilesInOrderLastModifiedDescending(Arrays.asList(Objects.requireNonNull(file.listFiles((dir, name) -> name.endsWith(".log")))));
        }
    }

    private static List<File> sortFilesInOrderLastModifiedDescending(List<File> files) {
        files.sort(Comparator.comparing(File::lastModified).reversed());
        return files;
    }
}
