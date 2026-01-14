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
        this("data/todo.csv");
    }

    public void add(String task, String status, String date) {
        try {
            FileWriter writer = new FileWriter(file, true);

            writer.write(task + "," + status + "," + date + "\n");
            writer.close();
        }
        catch (IOException e) {
            System.out.println("Got an error while adding a task: " + e);
        }
    }

    public void print() {
        try {
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                System.out.println(scanner.nextLine());
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        }
    }

    public void remove(int index) {
        try {
            Scanner scanner = new Scanner(file);

            List<String> lines = new ArrayList<String>();
            while (scanner.hasNextLine()) {
                lines.add(scanner.nextLine());
            }
            scanner.close();

            FileWriter writer = new FileWriter(file);
            for (int i = 0; i < lines.size(); i++) {
                if (i != index) {
                    writer.write(lines.get(i) + "\n");
                }
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Got an error while removing a task: " + e);
        }
    }
}