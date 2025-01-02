import java.sql.*;

public class Database {
    private final String PATH = "jdbc:mysql://localhost:3306/gymu_project";
    private String username = "root";
    private String password = "";

    private Connection con;
    private Statement st;
    private ResultSet rs;

    Database() {
        
    }    

    //Class.forName(“oracle.jdbc.driver.OracleDriver”); // needs to be in main 

    void write(String querry) {
        try {
            con = DriverManager.getConnection(PATH, username, password);
            st = con.createStatement();
            int response = st.executeUpdate(querry);
            if (response != 1)
                throw new ArithmeticException("The update was unsuccessful");

            con.close();

        } catch (Exception e) {
            System.out.println("There was an error while connecting to the database:\n" + e);
        }
    }

    String read(String querry, String column) {
        try{
            con = DriverManager.getConnection(PATH, username, password);
            st = con.createStatement();
            rs = st.executeQuery(querry);
            rs.next();
            String info = rs.getString(column);

            con.close();
            
            return info;
        } catch (Exception e) {
            System.out.println("There was an error while connecting to the database:\n" + e);
            return "";
        }
    }
}
