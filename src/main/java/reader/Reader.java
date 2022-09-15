package main.java.reader;

import java.io.File;
import java.util.List;

public interface Reader {
    List<File> readFilesFromDirectory(String directory);
}
