package main.domain;

import java.time.Period;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public record Statistics(long fileReadingTimeInMs, Period period, Map<Severity, Integer> severityCount,
                         Set<String> uniqueLibraries) {

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Statistics that = (Statistics) o;
        return fileReadingTimeInMs == that.fileReadingTimeInMs && Objects.equals(period, that.period) && Objects.equals(severityCount, that.severityCount) && Objects.equals(uniqueLibraries, that.uniqueLibraries);
    }
}
