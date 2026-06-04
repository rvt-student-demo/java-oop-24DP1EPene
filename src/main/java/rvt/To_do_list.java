package rvt;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class To_do_list {
    private String dbPath;
    private Connection connection;

    public To_do_list(String path) {
        this.dbPath = path;
        initializeDatabase();
    }

    public To_do_list() {
        this("data/todo.db");
    }

    private void initializeDatabase() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:" + dbPath);
            createTableIfNotExists();
        } catch (ClassNotFoundException e) {
            System.out.println("SQLite JDBC driver not found: " + e);
        } catch (SQLException e) {
            System.out.println("Error connecting to database: " + e);
        }
    }

    private void createTableIfNotExists() {
        String sql = "CREATE TABLE IF NOT EXISTS tasks (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "task TEXT NOT NULL," +
                "status TEXT NOT NULL," +
                "date TEXT NOT NULL" +
                ")";
        try (Statement stmt = connection.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println("Error creating table: " + e);
        }
    }

    public void add(String task, String status, String date) {
        String sql = "INSERT INTO tasks(task, status, date) VALUES(?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, task);
            pstmt.setString(2, status);
            pstmt.setString(3, date);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Got an error while adding a task: " + e);
        }
    }

    public List<String> getAll() {
        List<String> tasks = new ArrayList<>();
        String sql = "SELECT task, status, date FROM tasks";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                String task = rs.getString("task");
                String status = rs.getString("status");
                String date = rs.getString("date");
                tasks.add(task + "," + status + "," + date);
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving tasks: " + e);
        }
        return tasks;
    }

    public void remove(int index) {
        List<String> allTasks = getAll();
        if (index >= 0 && index < allTasks.size()) {
            String[] parts = allTasks.get(index).split(",");
            if (parts.length >= 3) {
                String task = parts[0];
                String status = parts[1];
                String date = parts[2];
                
                String sql = "DELETE FROM tasks WHERE task = ? AND status = ? AND date = ?";
                try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
                    pstmt.setString(1, task);
                    pstmt.setString(2, status);
                    pstmt.setString(3, date);
                    pstmt.executeUpdate();
                } catch (SQLException e) {
                    System.out.println("Got an error while removing a task: " + e);
                }
            }
        }
    }
}
