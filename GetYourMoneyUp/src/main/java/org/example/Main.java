package org.example;

import guis.LogInFormGUI;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run(){
                new LogInFormGUI().setVisible(true);
                //new DataFormGUI().setVisible(true); //test data window

                //lietotaja tests
                // System.out.println(MyJDBC.checkUser("user"));
            }
        });

        String url = "jdbc:mysql://localhost:3306/gymu_project";
        String user = "root";
        String password = "";

        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            System.out.println("Connection successful!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}