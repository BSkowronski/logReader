package main.java.reader;

import main.java.exceptions.DirectoryNotFoundException;
import java.io.File;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class LogFileReader implements Reader{
    public List<File> readFilesFromDirectory(String directory) {
        File file = new File(directory);
        if (!file.exists()){
            throw new DirectoryNotFoundException("Directory not found");
        } else {
            return sortFilesInOrderLastModifiedDescending(Arrays.asList(file.listFiles((dir, name) -> name.endsWith(".log"))));
        }
    }

    private static List<File> sortFilesInOrderLastModifiedDescending(List<File> files) {
        files.sort(Comparator.comparing(File::lastModified).reversed());
        return files;
    }
}
