package main.java.logreader;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

public class App {

    public static void main(String[] args) throws FileNotFoundException {
        Reader reader = new Reader();
        String directory = "C:\\logs";
        List<File> files = reader.readFilesFromDirectory(directory);
        for (File file : files) {
            try(Scanner sc = new Scanner(file)){
                while(sc.hasNextLine()){
                    System.out.println(sc.nextLine());
                }
            }
        }
    }
}


