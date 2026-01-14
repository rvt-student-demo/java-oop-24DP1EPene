package rvt;

public class App {
    public static void main(String[] args) {
        To_do_list my_list = new To_do_list();
        my_list.add("Buy groceries", "Pending", "2025-01-20");
        my_list.add("Study Java", "In Progress", "2025-01-22");
        my_list.add("Call dentist", "Pending", "2025-01-25");
        
        // Display all tasks
        my_list.print();
        
        // Remove a task at index 1 (Study Java)
        my_list.remove(1);

        my_list.add("Go for a walk", "Pending", "2025-01-21");
        
        // Display all tasks again
        my_list.print();
    }
}
