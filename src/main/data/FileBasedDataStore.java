package main.data;

import main.domain.Log;
import main.domain.Severity;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class FileBasedDataStore implements DataStore {
    private static final int INDEX_OF_DATA = 0;
    private static final int INDEX_OF_TIME = 1;
    private static final int INDEX_OF_SEVERITY = 2;
    private static final int INDEX_OF_LIBRARY = 3;
    private final String filePath;
    private final List<Log> logList;
    private long beginReadingTime;
    private long endReadingTime;
    StringBuilder builder;

    public FileBasedDataStore(File file) {
        this.filePath = file.getPath();
        this.logList = createLogList(loadLogsFromFile());
    }

    @Override
    public List<Log> getLogList() {
        return new ArrayList<>(logList);
    }

    public Long getReadingFileTime() {
        return endReadingTime - beginReadingTime;
    }

    private List<Log> createLogList(List<String> logStringList) {
        List<Log> logs = new ArrayList<>();
        for (String s : logStringList) {
            builder = new StringBuilder();
            String[] split = s.replace("  ", " ").split(" ");
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
            String date = builder.append(split[INDEX_OF_DATA]).append(" ").append(split[INDEX_OF_TIME].replace(",", ".")).toString();
            LocalDateTime time = LocalDateTime.parse(date, dateTimeFormatter);
            Log log = new Log(time, Severity.valueOf(split[INDEX_OF_SEVERITY].toUpperCase()), split[INDEX_OF_LIBRARY]);
            logs.add(log);
        }
        endReadingTime = System.currentTimeMillis();
        return logs;
    }

    private List<String> loadLogsFromFile() {
        beginReadingTime = System.currentTimeMillis();
        List<String> lines = readDataToList(filePath);
        List<String> logs = new ArrayList<>();
        builder = new StringBuilder();

        for (String currentLine : lines) {
            String[] split = currentLine.split(" ");
            builder.append(currentLine);
            if (isLineStartFromDate(split[0])) {
                logs.add(builder.toString());

            } else {
                String s = builder.toString();
                logs.set(logs.size() - 1, logs.get(logs.size() - 1).concat(s));

            }
            builder = new StringBuilder();
        }
        return logs;
    }

    private boolean isLineStartFromDate(String date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            format.parse(date);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    private List<String> readDataToList(String filePath) {
        try {
            Path of = Path.of(filePath);
            return Files.readAllLines(of);
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }
}
