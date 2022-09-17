package main;

import main.data.FileBasedDataStore;
import main.reader.LogFileReader;
import main.service.LogReaderService;
import main.view.View;

import java.io.File;
import java.util.List;

public class App {

    public static void main(String[] args) {
        String directory = "D:\\logs";

        LogFileReader logFileReader = new LogFileReader();
        List<File> files = logFileReader.readFilesFromDirectory(directory);

        for (File file : files) {
            LogReaderService logReaderService = new LogReaderService(new FileBasedDataStore(file));
            View view = new View(logReaderService.createStatistics());
            view.printStatistics();
        }
    }
}


