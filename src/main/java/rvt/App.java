package rvt;

public class App {
    public static void main(String[] args) {
        To_do_list my_list = new To_do_list();
        my_list.add("Jorking it!");
        my_list.add("Complete skibidi");
        my_list.add("Go away!");
        
        // Display all tasks
        my_list.print();
        
        // Remove a task at index 1 (Complete homework)
        my_list.remove(1);
        
        // Display all tasks again
        my_list.print();
    }
}
