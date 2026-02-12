package rvt;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import rvt.utils.InfoBank;


public class App {
    public static void main(String[] args) {
        InfoBank infoBank = new InfoBank();
        Scanner scanner = new Scanner(System.in);
        List<String> leaveCommands = new ArrayList<>();
        leaveCommands.add("quit");
        leaveCommands.add("leave");
        leaveCommands.add("stop");
        leaveCommands.add("terminate");
        leaveCommands.add("exit");
        String command = "";

        while (!leaveCommands.contains(command)) {
            System.out.print("Enter a command: ");
            command = scanner.nextLine().toLowerCase();
            switch (command) {
                case "register":
                    System.out.print("Enter name: ");
                    String name = scanner.nextLine();
                    while (!(name.matches("\\p{L}+") && name.length() > 2)) {
                        System.out.println("Invalid name!");
                        System.out.print("Enter name: ");
                        name = scanner.nextLine();
                    }

                    System.out.print("Enter surname: ");
                    String surname = scanner.nextLine();
                    while (!(surname.matches("\\p{L}+") && surname.length() > 2)) {
                        System.out.println("Invalid surname!");
                        System.out.print("Enter surname: ");
                        surname = scanner.nextLine();
                    }

                    System.out.print("Enter email: ");
                    String email = scanner.nextLine();
                    while (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
                        System.out.println("Invalid email format!");
                        System.out.print("Enter email: ");
                        email = scanner.nextLine();
                    }

                    System.out.print("Enter personal code: ");
                    String personalCode = scanner.nextLine();
                    while (!personalCode.matches("^\\d{7}-\\d{5}$") || infoBank.personalCodes.contains(personalCode)) {
                        System.out.println("Invalid personal code format or such a code already exists!");
                        System.out.print("Enter personal code: ");
                        personalCode = scanner.nextLine();
                    }

                    infoBank.add(name, surname, email, personalCode);
                    break;
                case "help":
                    System.out.println("Commands: register, list or print, remove, exit, clear, edit");
                    break;
                case "remove":
                    System.out.print("enter the id of the student to remove: ");
                    if (!infoBank.remove(scanner.nextLine())) {
                        System.out.println("No such id!");
                    }
                    break;
                case "print":
                case "list":
                    List<List<String>> data = infoBank.data;

                    int largest_id = 2;
                    int largest_name = 4;
                    int largest_surname = 7;
                    int largest_email = 5;
                    int largest_personal_code = 13;
                    int largest_date = 4;

                    if (data.size() > 0) {
                        for (int i = 0; i < data.size(); i++) {
                            List<String> parts = data.get(i);
                            if (parts.get(0).length() > largest_id) {
                                largest_id = parts.get(0).length();
                            }
                            if (parts.get(1).length() > largest_name) {
                                largest_name = parts.get(1).length();
                            }
                            if (parts.get(2).length() > largest_surname) {
                                largest_surname = parts.get(2).length();
                            }
                            if (parts.get(3).length() > largest_email) {
                                largest_email = parts.get(3).length();
                            }
                            if (parts.get(4).length() > largest_personal_code) {
                                largest_personal_code = parts.get(4).length();
                            }
                            if (parts.get(5).length() > largest_date) {
                                largest_date = parts.get(5).length();
                            }
                        }
                    String division_line = "+" + "-".repeat(largest_id) + "+" + "-".repeat(largest_name) + "+" + "-".repeat(largest_surname) + "+" + "-".repeat(largest_email) + "+" + "-".repeat(largest_personal_code) + "+" + "-".repeat(largest_date) + "+" + "\n";
                    System.out.print(division_line +
                            "|" + "ID" + " ".repeat(largest_id - 2) +
                            "|" + "Name" + " ".repeat(largest_name - 4) +
                            "|" + "Surname" + " ".repeat(largest_surname - 7) +
                            "|" + "Email" + " ".repeat(largest_email - 5) +
                            "|" + "Personal Code" + " ".repeat(largest_personal_code - 13) +
                            "|" + "Date" + " ".repeat(largest_date - 4) +
                            "|\n" + division_line);
                    for (int i = 0; i < data.size(); i++) {
                        List<String> parts = data.get(i);
                        System.out.println(
                                "|" + parts.get(0) + " ".repeat(largest_id - parts.get(0).length()) +
                                "|" + parts.get(1) + " ".repeat(largest_name - parts.get(1).length()) +
                                "|" + parts.get(2) + " ".repeat(largest_surname - parts.get(2).length()) +
                                "|" + parts.get(3) + " ".repeat(largest_email - parts.get(3).length()) +
                                "|" + parts.get(4) + " ".repeat(largest_personal_code - parts.get(4).length()) +
                                "|" + parts.get(5) + " ".repeat(largest_date - parts.get(5).length()) +
                                "|"
                        );
                    }
                    System.out.println(division_line);
                    }
                    else {
                        System.out.println("No Students have been saved!");
                    }
                    break;
                case "clear":
                case "cls":
                    // Try OS command with inheritIO
                    String os = System.getProperty("os.name").toLowerCase();
                    ProcessBuilder pb = os.contains("win")
                        ? new ProcessBuilder("cmd", "/c", "cls")
                        : new ProcessBuilder("clear");
                    try {
                        pb.inheritIO().start().waitFor();
                    }
                    catch (Exception e) {
                        // Fallback for IDEs / unsupported consoles
                        for (int i = 0; i < 50; i++) System.out.println();
                    }
                    break;
                case "edit":
                    // Update, so that you can leave the fields empty and check if such an id exists
                    System.out.print("Enter the students id: ");
                    String id = scanner.nextLine();
                    if (!infoBank.idList.contains(id)) {
                        System.out.println("No such id!");
                        break;
                    }
                    List<String> student = new ArrayList<String>();
                    student.add(id);
                    System.out.print("Enter new name: ");
                    String new_name = scanner.nextLine();
                    if (new_name.length() > 0) {
                        while (!(new_name.matches("\\p{L}+") && new_name.length() > 2)) {
                            System.out.println("Invalid name!");
                            System.out.print("Enter new name: ");
                            new_name = scanner.nextLine();
                        }
                    }
                    student.add(new_name);

                    System.out.print("Enter new surname: ");
                    String new_surname = scanner.nextLine();
                    if (new_surname.length() > 0) {
                        while (!(new_surname.matches("\\p{L}+") && new_surname.length() > 2)) {
                            System.out.println("Invalid surname!");
                            System.out.print("Enter new surname: ");
                            new_surname = scanner.nextLine();
                        }
                    }
                    student.add(new_surname);

                    System.out.print("Enter new email: ");
                    String new_email = scanner.nextLine();
                    if (new_email.length() > 0) {
                        while (!new_email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
                            System.out.println("Invalid email format!");
                            System.out.print("Enter new email: ");
                            new_email = scanner.nextLine();
                        }
                    }
                    student.add(new_email);

                    System.out.print("Enter new personal code: ");
                    String new_personal_code = scanner.nextLine();
                    if (new_personal_code.length() > 0) {
                        while (!new_personal_code.matches("^\\d{7}-\\d{5}$")) {
                            System.out.println("Invalid personal code format!");
                            System.out.print("Enter new personal code: ");
                            new_personal_code = scanner.nextLine();
                        }
                    }
                    student.add(new_personal_code);
                    infoBank.update(id, student);
                    break;
                default:
                    if (!leaveCommands.contains(command)) {
                        System.out.println("Unknown command!");
                    }
                    break;
            }
        }
        infoBank.updateFile();
        scanner.close();
        System.out.println("Successfully left!");
    }
}
