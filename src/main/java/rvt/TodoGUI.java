package rvt;

import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class TodoGUI extends Frame {

    private To_do_list todo;
    private TextField taskField;
    private TextField statusField;
    private TextField dateField;

    private List<String> tasks;

    public TodoGUI() {
        super("To‑Do List (AWT Graphics)");
        todo = new To_do_list();
        tasks = todo.getAll();

        setSize(600, 400);
        setLayout(new FlowLayout());

        taskField = new TextField("Task", 15);
        statusField = new TextField("Status", 10);
        dateField = new TextField("YYYY‑MM‑DD", 10);

        Button addButton = new Button("Add Task");

        add(taskField);
        add(statusField);
        add(dateField);
        add(addButton);

        addButton.addActionListener(e -> {
            todo.add(taskField.getText(), statusField.getText(), dateField.getText());
            tasks = todo.getAll();
            repaint();
        });

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

        addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int y = e.getY();
                int index = (y - 120) / 25; // row height
                if (index >= 0 && index < tasks.size()) {
                    todo.remove(index);
                    tasks = todo.getAll();
                    repaint();
                }
            }
        });

        setVisible(true);
    }

    @Override
    public void paint(Graphics g) {
        g.setFont(new Font("Arial", Font.PLAIN, 16));
        g.drawString("Click a task to remove it:", 50, 100);

        int y = 130;
        for (int i = 0; i < tasks.size(); i++) {
            g.drawString(i + ": " + tasks.get(i), 50, y);
            y += 25;
        }
    }
}
