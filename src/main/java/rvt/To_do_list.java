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
    private List<String> lines = new ArrayList<String>();
    private Scanner scanner;
    private int last_id = 0;

    public To_do_list(String path) {
        this.file = new File(path);
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            System.out.println("File wasn't found at: " + file.toPath());
        }
        while (scanner.hasNextLine()) {
            String next_line = scanner.nextLine();
            int line_id = Integer.valueOf(next_line.split(",")[0]);
            if (line_id > last_id) { last_id = line_id; }
            lines.add(next_line);
        }
        scanner.close();
    }

    public To_do_list() {
        this("data/todo.csv");
    }

    public void add(String task) {
        try {
            FileWriter writer = new FileWriter(file, true);
            last_id += 1;
            String line = last_id + ", " + task + "\n";

            writer.write(line);
            writer.close();

            lines.add(line);
        }
        catch (IOException e) {
            System.out.println("Got an error while adding a task: " + e);
        }
    }

    public void print() {
        for (int i = 0; i < lines.size(); i++) {
            System.out.println(lines.get(i).replace(',', ':'));
        }
    }

    public void remove(int id) {
        FileWriter writer;
        try {
            writer = new FileWriter(file);
            for (int i = 0; i < lines.size(); i++) {
                if (Integer.valueOf(lines.get(i).split(",")[0]) != id) {
                    writer.write(lines.get(i) + "\n");
                }
                else {
                    lines.remove(i);
                }
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("IOException while removing task | Error: " + e);
        }
    }

    public int get_last_id() {
        return last_id;
    }
}