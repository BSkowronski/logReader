package main.view;

import main.domain.Severity;
import main.domain.Statistics;

import java.time.Period;
import java.util.Map;
import java.util.Set;

import static main.domain.Severity.*;

public class View {
    Statistics statistics;

    public View(Statistics statistics) {
        this.statistics = statistics;
    }

    public void printStatistics() {
        printFileReadingTime(statistics.fileReadingTimeInMs());
        printPeriod(statistics.period());
        printLogSeverity(statistics.severityCount());
        printLogsRatio(statistics.severityCount());
        printUniqueLibrariesCount(statistics.uniqueLibraries());
        System.out.println();
    }

    private void printLogsRatio(Map<Severity, Integer> severityCount) {
        int errorSeverityCount = severityCount.get(ERROR);
        int fatalSeverityCount = severityCount.get(FATAL);
        int sumOfCount = severityCount.values().stream().mapToInt(i -> i).sum();

        double ratio = (double) (errorSeverityCount + fatalSeverityCount) / sumOfCount;

        System.out.println("Ratio of the number of logs with severity ERROR or higher to all logs:"
                + ratio + ".");
    }

    private void printLogSeverity(Map<Severity, Integer> severityCount) {
        System.out.println("Severity count: " + severityCount);
    }

    private void printPeriod(Period period) {
        System.out.println("Log range: "
                + period.getYears() + " years, "
                + period.getMonths() + " months, "
                + period.getDays() + "days.");
    }

    private void printFileReadingTime(long fileReadingTimeInMs) {
        System.out.println("Time elapsed for reading file: " + fileReadingTimeInMs + "ms.");
    }

    private void printUniqueLibrariesCount(Set<String> uniqueLibraries) {
        System.out.println("Number of unique libraries: " + uniqueLibraries.size() + "\n" + "Unique Libraries:" + uniqueLibraries);

    }
}
