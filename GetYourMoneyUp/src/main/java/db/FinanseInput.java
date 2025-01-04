package db;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class FinanseInput {
    private static int finanse_id;
    private static int userId;
    private static String date;
    private static String category;
    String query;
    private static Database DB = new Database();
    Scanner sc = new Scanner(System.in);


    public FinanseInput(int userId) {
        this.userId = userId;
        date = currentDate();
        category = "not specified";
    }

    private static boolean updateFinanseId() {
        String query = "SELECT izmaksas_id FROM izmaksas ORDER BY izmaksas_id DESC;";
        String response = DB.read(query, "izmaksas_id");

        try {
            finanse_id = Integer.parseInt(response) + 1;
            return true;

        } catch (NumberFormatException e) {
            System.out.println("Invalid integer input");
            return false;
        }
    }

    private String currentDate() {
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

        return currentDate.format(formatter);
    }

    public static void dataInput(double sum) {
        if (updateFinanseId()) {
            String query = "INSERT INTO izmaksas VALUES (" + finanse_id + ", " + userId + ", " + sum + ", '" + date + "', '" + category + "');";
            DB.write(query);
        }
    }

    public static void dataUpdate(double sum) {
        int izmaksas_id = 1;
        String query = "UPDATE izmaksas SET sum = " + sum + " WHERE izmaksas_id == " + izmaksas_id;

        DB.write(query);
    }
}