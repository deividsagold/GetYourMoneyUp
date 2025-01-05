package db;

public class MyJDBC {
    private static Database db = new Database();

    public static boolean register(String username, String password) {
        if (!checkUser(username)) {
            String query = "SELECT user_id FROM user ORDER BY user_id DESC;";
            String response = db.read(query, "user_id");
            if (response == null || response.isEmpty())
                response = "-1";
            try {
                int userId = Integer.parseInt(response) + 1;
                query = "INSERT INTO user (user_id, username, password) VALUES (" + userId + ", '" + username + "', '" + password + "');";
                db.write(query);
                return true;
            } catch (NumberFormatException e) {
                System.out.println("Invalid integer input");
            }
        }
        return false;
    }

    public static boolean checkUser(String username) {
        String query = "SELECT username FROM user WHERE username = '" + username + "';";
        String response = db.read(query, "username");

        if (response.equals(username))
            return true;
        else
            return false;
    }

    public static boolean validateLogin(String username, String password) {
        String query = "SELECT username, password FROM user WHERE username = '" + username + "';";
        String response = db.read(query, "username");

        if (response.equals(username)) {
            response = db.read(query, "password");
            if (response.equals(password))
                return true;
        }
        return false;
    }

    public static int returnUserId(String username) {
        String query = "SELECT user_id FROM user WHERE username = '" + username + "';";
        String response = db.read(query, "user_id");
        try {
            int userId = Integer.parseInt(response);
            return userId;
        } catch (NumberFormatException e) {
            System.out.println("Invalid integer input");
            return -1;
        }
    }
}
