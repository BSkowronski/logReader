package main.java.logreader;

import main.java.data.DataStore;
import main.java.data.FileBasedDataStore;
import main.java.domain.Log;
import main.java.reader.LogFileReader;
import java.io.*;
import java.util.List;

public class App {

    public static void main(String[] args){

        LogFileReader logFileReader = new LogFileReader();
        String directory = "C:\\logs";
        List<File> files = logFileReader.readFilesFromDirectory(directory);
        DataStore dataStore = new FileBasedDataStore(files.get(0).getPath());
        List<Log> logs = dataStore.getLogList();
        for (Log log : logs) {
            System.out.println(log);
        }
    }


}


