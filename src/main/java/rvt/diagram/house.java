package rvt.diagram;

// Packages to import
import javax.swing.*;

public class house {
    public static void main(String[] args) {
        // Create a JFrame to hold the diagram
        JFrame frame = new JFrame("House Diagram");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);

        // Create a JPanel to draw the house
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(java.awt.Graphics g) {
                super.paintComponent(g);
                // Draw the base of the house
                g.drawRect(100, 200, 200, 150);
                // Draw the roof of the house
                g.drawLine(100, 200, 200, 100);
                g.drawLine(300, 200, 200, 100);
                // Draw the door
                g.drawRect(180, 300, 40, 50);
                // Draw windows
                g.drawRect(120, 220, 40, 40);
                g.drawRect(240, 220, 40, 40);
            }
        };

        // Add the panel to the frame and make it visible
        frame.add(panel);
        frame.setVisible(true);
    }
}
