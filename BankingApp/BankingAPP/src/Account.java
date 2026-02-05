import java.sql.*;
import java.util.Random;

public class Account {

    private Connection con;

    public Account() {
        con = DBConnection.getConnection();
    }

    public long generate_account_number() {
        Random r = new Random();
        return 1000000000L + r.nextInt(900000000);
    }

    public boolean account_exist(int userId) {
        try {
            PreparedStatement ps = con.prepareStatement(
                    "SELECT account_no FROM accounts WHERE user_id=?"
            );
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            return rs.next();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public void open_account(int userId) {
        try {
            if (account_exist(userId)) {
                System.out.println("Account already exists!");
                return;
            }

            long accNo = generate_account_number();

            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO accounts(account_no, user_id, balance) VALUES(?,?,0)"
            );

            ps.setLong(1, accNo);
            ps.setInt(2, userId);
            ps.executeUpdate();

            System.out.println("Account Created Successfully");
            System.out.println("Account Number: " + accNo);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public long get_account_number(int userId) {
        try {
            PreparedStatement ps = con.prepareStatement(
                    "SELECT account_no FROM accounts WHERE user_id=?"
            );
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getLong(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }
}
