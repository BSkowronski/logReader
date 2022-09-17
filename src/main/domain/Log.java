package main.domain;

import java.time.LocalDateTime;
import java.util.Objects;

public record Log(LocalDateTime time, Severity severity, String library) {

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Log log = (Log) o;
        return Objects.equals(time, log.time) && Objects.equals(severity, log.severity) && Objects.equals(library, log.library);
    }

}
