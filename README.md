# Banking Application using Java & JDBC

## 📌 Project Description
This is a **console-based Banking Application** developed using **Java** and **JDBC** with **MySQL** as the backend database.  
The project simulates basic banking operations such as **user registration, login, account creation, money credit, debit, transfer, and balance enquiry**.

The application follows a **layered and modular approach**, where each class has a specific responsibility.

---

## 🛠 Technologies Used
- Java (JDK 8+)
- JDBC (Java Database Connectivity)
- MySQL Database
- VS Code
- MySQL Connector/J

---

## 📂 Project Structure
BankingApp
│
├── lib
│ └── mysql-connector-j-8.3.0.jar
│
├── src
│ ├── DBConnection.java
│ ├── User.java
│ ├── Account.java
│ ├── AccountManager.java
│ └── BankingApp.java
│
└── README.md

---

## 🗄 Database Schema

### Database Name
CREATE DATABASE banking_app;
USE banking_app;

CREATE TABLE users (
    user_id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) UNIQUE,
    password VARCHAR(50)
);

CREATE TABLE accounts (
    account_no BIGINT PRIMARY KEY,
    user_id INT,
    balance DOUBLE DEFAULT 0,
    FOREIGN KEY (user_id) REFERENCES users(user_id)
);

### Tables

**users**
- user_id (Primary Key)
- username
- password

**accounts**
- account_no (Primary Key)
- user_id (Foreign Key)
- balance

---

## ⚙️ Functionalities
- User Registration
- User Login
- Account Creation
- Credit Money
- Debit Money (with balance check)
- Money Transfer (with transaction handling)
- Balance Enquiry
- Logout & Exit

---

## 🔗 Class Responsibilities

### DBConnection.java
- Loads MySQL JDBC driver
- Establishes database connection
- Provides a single reusable connection

### User.java
- Register new users
- Login existing users
- Check if user already exists

### Account.java
- Generate account number
- Open new bank account
- Check if account exists
- Fetch account number

### AccountManager.java
- Credit money
- Debit money
- Transfer money between accounts
- Check account balance

### BankingApp.java
- Main menu-driven application
- Creates objects of all classes
- Controls application flow

---

## ▶️ How to Run the Project in VS Code

1. Install **JDK** and **MySQL**
2. Download **MySQL Connector/J**
3. Place the connector JAR inside `lib` folder
4. Add JAR to Java classpath in VS Code
5. Create database and tables using provided SQL
6. Update database credentials in `DBConnection.java`
7. Run `BankingApp.java`

---

## 🚀 Sample Output
Enter choice: 1
Enter username: shivam
Enter password: 1234
Account Created Successfully

---

## ✅ Advantages
- Secure database interaction using PreparedStatement
- Transaction management for money transfer
- Modular and easy-to-understand code
- Real-world banking logic simulation

---

## 📌 Conclusion
This Banking Application demonstrates the practical use of **Java JDBC with MySQL** to build a real-world console-based system.  
It can be further enhanced by adding GUI, encryption, and role-based access.

---

## 👨‍💻 Author
**Shivam Nayak**  
(Java & Backend Developer – Student Project)

