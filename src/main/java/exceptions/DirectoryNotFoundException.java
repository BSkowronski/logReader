package main.java.exceptions;

public class DirectoryNotFoundException extends RuntimeException {
    public DirectoryNotFoundException(String message) {
        super(message);
    }
}
