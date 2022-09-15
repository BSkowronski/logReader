package main.java.data;

import main.java.domain.Log;

import java.util.List;

public interface DataStore {
    List<Log> getLogList();
}
