import java.sql.Connection;
import java.sql.DriverManager;

public class App {
    public static void main(String[] args) throws Exception {
        Connection com = DriverManager.getConnection(
    "jdbc:mysql://localhost:3306/bank",
    "root",
    "Shivam@2005"
);

        System.out.println("Connection established successfully!");
        com.close();
    }
}
