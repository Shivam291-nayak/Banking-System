import java.sql.*;
import java.util.Scanner;

public class AccountManager {

    private Connection con;
    private Scanner sc;

    public AccountManager() {
        con = DBConnection.getConnection();
        sc = new Scanner(System.in);
    }

    public void credit_money(long accNo) {
        try {
            System.out.print("Enter amount: ");
            double amount = sc.nextDouble();

            PreparedStatement ps = con.prepareStatement(
                    "UPDATE accounts SET balance = balance + ? WHERE account_no=?"
            );

            ps.setDouble(1, amount);
            ps.setLong(2, accNo);
            ps.executeUpdate();

            System.out.println("Amount credited successfully");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void debit_money(long accNo) {
        try {
            double balance = check_balance(accNo);

            System.out.print("Enter amount: ");
            double amount = sc.nextDouble();

            if (amount > balance) {
                System.out.println("Insufficient Balance!");
                return;
            }

            PreparedStatement ps = con.prepareStatement(
                    "UPDATE accounts SET balance = balance - ? WHERE account_no=?"
            );

            ps.setDouble(1, amount);
            ps.setLong(2, accNo);
            ps.executeUpdate();

            System.out.println("Amount debited successfully");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void transfer_money(long fromAcc) {
        try {
            System.out.print("Enter receiver account: ");
            long toAcc = sc.nextLong();

            System.out.print("Enter amount: ");
            double amount = sc.nextDouble();

            double balance = check_balance(fromAcc);

            if (amount > balance) {
                System.out.println("Insufficient Balance!");
                return;
            }

            con.setAutoCommit(false);

            PreparedStatement debit = con.prepareStatement(
                    "UPDATE accounts SET balance = balance - ? WHERE account_no=?"
            );

            PreparedStatement credit = con.prepareStatement(
                    "UPDATE accounts SET balance = balance + ? WHERE account_no=?"
            );

            debit.setDouble(1, amount);
            debit.setLong(2, fromAcc);

            credit.setDouble(1, amount);
            credit.setLong(2, toAcc);

            debit.executeUpdate();
            credit.executeUpdate();

            con.commit();
            System.out.println("Transfer Successful");

        } catch (Exception e) {
            try {
                con.rollback();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public double check_balance(long accNo) {
        try {
            PreparedStatement ps = con.prepareStatement(
                    "SELECT balance FROM accounts WHERE account_no=?"
            );
            ps.setLong(1, accNo);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getDouble(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}
