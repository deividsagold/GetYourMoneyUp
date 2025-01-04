package db;

import java.sql.*;
public class Database {
    private static final String PATH = "jdbc:mysql://localhost:3306/gymu_project";
    private static final String username = "root";
    private static final String password = "";

    private static Connection con;
    private static Statement st;
    private static ResultSet rs;

    Database() {
        
    }    

    //Class.forName(“oracle.jdbc.driver.OracleDriver”); // needs to be in main 

    public static void write(String query) {
        try {
            con = DriverManager.getConnection(PATH, username, password);
            st = con.createStatement();
            int response = st.executeUpdate(query);
            if (response != 1)
                throw new ArithmeticException("The update was unsuccessful");

            con.close();

        } catch (Exception e) {
            System.out.println("There was an error while connecting to the database:\n" + e);
        }
    }

    public static String read(String query, String column) {
        try{
            con = DriverManager.getConnection(PATH, username, password);
            st = con.createStatement();
            rs = st.executeQuery(query);
            rs.next();
            String info = rs.getString(column);

            con.close();
            
            return info;
        } catch (Exception e) {
            System.out.println("There was an error while connecting to the database:\n" + e);
            return "";
        }
    }

    public static void readSmallArray(String[] array, String query, String column) {
        try{
            con = DriverManager.getConnection(PATH, username, password);
            st = con.createStatement();
            rs = st.executeQuery(query);
            int i = 0;
            while (rs.next() && i < array.length) {
                array[i] = rs.getString(column);
                i++;
            }
            con.close();
        } catch (Exception e) {
            System.out.println("There was an error while connecting to the database:\n" + e);
        }
    }

    public static double returnColumnSum(String query, String column) {
        try{
            con = DriverManager.getConnection(PATH, username, password);
            st = con.createStatement();
            rs = st.executeQuery(query);
            double sum = 0.0;
            String response;
            while (rs.next()) {
                response = rs.getString(column);
                sum += Double.parseDouble(response);
            }
            con.close();
            return sum;
        } catch (Exception e) {
            System.out.println("There was an error while connecting to the database:\n" + e);
            return -0.001;
        }
    }
}
