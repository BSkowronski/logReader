package main.data;

import main.domain.Log;

import java.util.List;

public interface DataStore {
    List<Log> getLogList();

    Long getReadingFileTime();
}
