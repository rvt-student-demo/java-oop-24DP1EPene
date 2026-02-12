package rvt.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class InfoBank {
    private File file;
    private Scanner scanner;

    public List<List<String>> data = new ArrayList<>();
    public List<String> personalCodes = new ArrayList<String>();
    public List<String> idList = new ArrayList<String>();

    public InfoBank(String path) {
        this.file = new File(path);
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            System.out.println("File wasn't found at: " + file.toPath());
        }
        while (scanner.hasNextLine()) {
            List<String> user = Arrays.asList(scanner.nextLine().split(","));
            data.add(user);
            personalCodes.add(user.get(4));
            idList.add(user.get(0));
        }
        scanner.close();
    }

    public InfoBank() {
        this("data/infoBank.csv");
    }

    public void add(String name, String surname, String email, String personalCode) {
        try { 
            FileWriter writer = new FileWriter(file, true);
            String line = String.format("%s, %s, %s, %s, %s, %s", java.util.UUID.randomUUID().toString(), name, surname, email, personalCode, LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

            writer.write(line + "\n");
            writer.close();
            data.add(Arrays.asList(line.split(",")));
        }
        catch (IOException e) {
            System.out.println("Got an error while adding a task: " + e);
        }
    }

    public boolean remove(String id) {
        boolean removed = false;
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).get(0).equals(id)) {
                data.remove(i);
                removed = true;
                break;
            }
        }
        return removed;
    }

    public void update(String id, List<String> updatedData) {
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).get(0).equals(id)) {
                data.set(i, updatedData);
            }
        }
    }

    public void updateFile() {
        try {
            FileWriter fileWriter = new FileWriter(file);
            for (int i = 0; i < data.size(); i++) {
                fileWriter.write(String.join(",", data.get(i)) + "\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("IOException occurred | Error: " + e);
        }
    }
}