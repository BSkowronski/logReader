package main.service;

import main.data.DataStore;
import main.domain.Log;
import main.domain.Severity;
import main.domain.Statistics;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.*;
import java.util.stream.Collectors;

public class LogReaderService {
    public static final Severity INFO = Severity.INFO;
    public static final Severity DEBUG = Severity.DEBUG;
    public static final Severity ERROR = Severity.ERROR;
    public static final Severity FATAL = Severity.FATAL;
    private final DataStore dataStore;

    public LogReaderService(DataStore dataStore) {
        this.dataStore = dataStore;
    }

    public Statistics createStatistics() {
        List<Log> logList = dataStore.getLogList();
        logList.sort(Comparator.comparing(Log::time));

        return new Statistics(dataStore.getReadingFileTime(), calculateDatePeriod(logList), calculateSeverityCount(logList), createUniqueLibrariesSet(logList));
    }

    private Set<String> createUniqueLibrariesSet(List<Log> logList) {
        return logList.stream()
                .map(Log::library)
                .collect(Collectors.toSet());
    }

    private Map<Severity, Integer> calculateSeverityCount(List<Log> logList) {
        Map<Severity, List<Log>> logMap = logList.stream()
                .collect(Collectors.groupingBy(Log::severity));

        Map<Severity, Integer> severityCount = new EnumMap<>(Severity.class);

        checkIfSeverityExistInLog(logMap, INFO, severityCount, Severity.INFO);
        checkIfSeverityExistInLog(logMap, DEBUG, severityCount, Severity.DEBUG);
        checkIfSeverityExistInLog(logMap, ERROR, severityCount, Severity.ERROR);
        checkIfSeverityExistInLog(logMap, FATAL, severityCount, Severity.FATAL);

        return severityCount;
    }

    private void checkIfSeverityExistInLog(Map<Severity, List<Log>> logMap, Severity info, Map<Severity, Integer> severityCount, Severity info1) {
        if (logMap.containsKey(info)) {
            severityCount.put(info, logMap.get(info1).size());
        } else {
            severityCount.put(info, 0);
        }
    }

    private Period calculateDatePeriod(List<Log> logList) {
        List<LocalDateTime> localDateTimes = logList.stream()
                .map(Log::time)
                .toList();
        return Period.between(localDateTimes.get(0).toLocalDate(), localDateTimes.get(localDateTimes.size() - 1).toLocalDate());
    }
}
