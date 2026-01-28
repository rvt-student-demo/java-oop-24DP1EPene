package rvt;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        To_do_list my_list = new To_do_list();
        Scanner scanner = new Scanner(System.in);
        String command = "";

        while (!command.equals("stop")) {
            System.out.print("Command: ");
            command = scanner.nextLine();
            switch (command) {
                case "add":
                    System.out.print("Task: ");
                    String task = scanner.nextLine();
                    if (task.length() >= 3 && task.matches("[A-Za-z0-9\\s]")) {
                        my_list.add(task);
                    }
                    break;
                case "list":
                    my_list.print();
                    break;
                case "completed", "remove":
                    System.out.print("Enter the id of the task: ");
                    my_list.remove(Integer.valueOf(scanner.nextLine()));
                    break;
                case "help":
                    System.out.println("All available commands: \n\tAdd\n\tlist\n\tcompleted\n\tstop");
            }
        }
        scanner.close();
        System.out.println("Successfully left!");
    }
}
