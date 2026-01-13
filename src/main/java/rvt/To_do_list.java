package rvt;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class To_do_list {
    private File file;

    public To_do_list(String path) {
        this.file = new File(path);
    }

    public To_do_list() {
        this.file = new File("data/data.csv");
    }

    public void add(String task) {
        try {
            FileWriter writer = new FileWriter(file, true);
            writer.write(task);
        }
        catch (IOException e) {
            System.out.println("Got an error: " + e);
        }
    }

    public void print() {
        Scanner scanner;
        try {
            scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                System.out.printf(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {}
    }

    public void remove(int i) {
        tasks.remove(i);
    }
}