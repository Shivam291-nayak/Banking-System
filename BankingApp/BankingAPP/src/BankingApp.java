import java.util.Scanner;

public class BankingApp {

    public static void main(String[] args) {

        try (Scanner sc = new Scanner(System.in)) {
            User user = new User();
            Account account = new Account();
            AccountManager manager = new AccountManager();

            while (true) {
                System.out.println("\n1.Register\n2.Login\n3.Exit");
                int choice = sc.nextInt();

            if (choice == 1) {
                int userId = user.register();
                if (userId != -1) {
                    account.open_account(userId);
                }

            } else if (choice == 2) {
                int userId = user.login();
                if (userId != -1) {

                    long accNo = account.get_account_number(userId);

                    while (true) {
                        System.out.println(
                                "\n1.Credit\n2.Debit\n3.Transfer\n4.Check Balance\n5.Logout"
                        );

                        int opt = sc.nextInt();

                        if (opt == 1)
                            manager.credit_money(accNo);
                        else if (opt == 2)
                            manager.debit_money(accNo);
                        else if (opt == 3)
                            manager.transfer_money(accNo);
                        else if (opt == 4)
                            System.out.println("Balance: " + manager.check_balance(accNo));
                        else
                            break;
                    }
                }

            } else {
                System.out.println("Thank you!");
                System.exit(0);
            }
            // Scanner 'sc' is automatically closed here
        }
    }
}
}