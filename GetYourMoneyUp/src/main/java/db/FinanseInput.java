package db;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class FinanseInput {
    int userId;
    double sum;
    String date;
    String category;

    String query;
    Database DB = new Database();
    Scanner sc = new Scanner(System.in);


    FinanseInput(int userId) {
        this.userId = userId;
        date = currentDate();
        category = "not specified";
    }

    private String currentDate() {
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

        return currentDate.format(formatter);
    }

    void dataInput() {
        System.out.println("Ievadiet šodienas summu: ");
        sum = sc.nextDouble();
        sum = 0.0 + sum;

        System.out.println("Ievadiet kategoriju: ");
        category = sc.next();

        String query = "INSERT INTO izmaksas (user_id, sum, date, category) VALUES (" + userId + ", " + sum + ", '" + date + "', '" + category + "')";

        DB.write(query);
    }

    void dataUpdate() {
        int izmaksas_id = 1;
        String query = "UPDATE izmaksas SET sum = " + sum + ", category = '" + category + "' WHERE izmaksas_id == " + izmaksas_id;

        DB.write(query);
    }
}