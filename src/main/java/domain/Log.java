package main.java.domain;

import java.time.LocalDateTime;
import java.util.Objects;

public class Log {
    final LocalDateTime time;
    final Severity severity;
    final String library;

    @Override
    public String toString() {
        return "Log{" +
                "time=" + time +
                ", severity=" + severity +
                ", library='" + library + "}";
    }

    public Log(LocalDateTime time, Severity severity, String library) {
        this.time = time;
        this.severity = severity;
        this.library = library;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Log log = (Log) o;
        return Objects.equals(time, log.time) && Objects.equals(severity, log.severity) && Objects.equals(library, log.library);
    }

    @Override
    public int hashCode() {
        return Objects.hash(time, severity, library);
    }
}
