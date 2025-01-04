package db;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class FinanseInput {
    private static int finanse_id;
    private static int userId;
    private static String date;
    private static String category;
    String query;
    private static Database DB = new Database();


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

    public static void returnSmallData(String[] dataArray, String date) {
        String query = "SELECT sum FROM izmaksas WHERE user_id == " + userId + " AND date == '" + date + "' ORDER BY izmaksas_id DESC;";
        DB.readSmallArray(dataArray, query, "sum");
    }

    public static String returnWholeSum(String date) {
        String query = "SELECT sum FROM izmaksas WHERE user_id == " + userId + " AND date == '" + date + "';";
        double sum = DB.returnColumnSum(query, "sum");
        String text = "" + sum;
        return text;
    }

    public static boolean checkForValidDate(String date) {
        String datePattern = "^\\d{2}\\.\\d{2}\\.\\d{4}$";
        Pattern pattern = Pattern.compile(datePattern);
        Matcher matcher = pattern.matcher(date);

        if (matcher.matches())
            return true;
        else
            return false;
    }
}