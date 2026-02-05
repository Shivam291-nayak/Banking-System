import java.sql.*;
import java.util.Scanner;

public class User {

    private Connection con;
    private Scanner sc;

    public User() {
        con = DBConnection.getConnection();
        sc = new Scanner(System.in);
    }

    public int register() {
        try {
            System.out.print("Enter username: ");
            String username = sc.next();
            System.out.print("Enter password: ");
            String password = sc.next();

            if (user_exist(username)) {
                System.out.println("User already exists!");
                return -1;
            }

            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO users(username, password) VALUES (?, ?)",
                    Statement.RETURN_GENERATED_KEYS
            );

            ps.setString(1, username);
            ps.setString(2, password);
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    public int login() {
        try {
            System.out.print("Enter username: ");
            String username = sc.next();
            System.out.print("Enter password: ");
            String password = sc.next();

            PreparedStatement ps = con.prepareStatement(
                    "SELECT user_id FROM users WHERE username=? AND password=?"
            );

            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt("user_id");
            } else {
                System.out.println("Invalid Login!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    public boolean user_exist(String username) {
        try {
            PreparedStatement ps = con.prepareStatement(
                    "SELECT user_id FROM users WHERE username=?"
            );
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            return rs.next();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
