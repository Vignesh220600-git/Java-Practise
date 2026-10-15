package org.example;

import java.sql.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    private static final String URL = "jdbc:mysql://localhost:3306/college";
    private static final String USER = "root";   // change if different
    private static final String PASSWORD = "Bharath01"; // change to your MySQL password

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
    public static void insertStudent(String name, int age) {
        String sql = "INSERT INTO " +
                    "students (name, age) " +
                    "VALUES ( ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, name);
            stmt.setInt(2, age);

            int rows = stmt.executeUpdate();
            if(rows ==1) {
                System.out.println(rows + " student inserted successfully.");
            } else {
                System.out.println( "Try again letter");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void getAllStudents() {
        String sql = "SELECT * FROM students";

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            System.out.println("id" + " | " +
                    "name" + " | " +
                    "age" + " | ");
            while (rs.next()) {
                System.out.println(rs.getInt("id") + " | " +
                        rs.getString("name") + " | " +
                        rs.getInt("age") + " | ");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void updateStudent(int id, int age) {
        String sql = "UPDATE students SET age = ? WHERE id = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, age);
            stmt.setInt(2, id);

            int rows = stmt.executeUpdate();
            System.out.println(rows + " student updated successfully.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void deleteStudent(int id) {
        String sql = "DELETE FROM students WHERE id = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            int rows = stmt.executeUpdate();
            System.out.println(rows + " student deleted successfully.");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
        public static void main(String[] args) {
//        insertStudent("Dhruv",25);
//        getAllStudents();
//        updateStudent(2,24);
//            deleteStudent(2);
    }

}