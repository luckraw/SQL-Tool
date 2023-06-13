import java.sql.*;
import java.util.Scanner;
import java.io.*;

public class SQLTool {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/database";
    private static final String DB_USERNAME = "username";
    private static final String DB_PASSWORD = "password";

    public static void main(String[] args) {
        try {
            Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);

            System.out.println("=== SQLTool ===");

            if (!authenticateUser(conn)) {
                System.out.println("Authentication failed. Exiting SQLTool...");
                conn.close();
                return;
            }

            while (true) {
                System.out.println("Options:");
                System.out.println("1. Data Query");
                System.out.println("2. Data Insertion");
                System.out.println("3. Data Update");
                System.out.println("4. Data Deletion");
                System.out.println("5. Generate Report");
                System.out.println("6. Exit");
                System.out.print("Choose an option: ");

                Scanner scanner = new Scanner(System.in);
                int option = scanner.nextInt();
                scanner.nextLine();

                switch (option) {
                    case 1:
                        executeQuery(conn);
                        break;
                    case 2:
                        executeInsert(conn);
                        break;
                    case 3:
                        executeUpdate(conn);
                        break;
                    case 4:
                        executeDelete(conn);
                        break;
                    case 5:
                        generateReport(conn);
                        break;
                    case 6:
                        System.out.println("Exiting SQLTool...");
                        conn.close();
                        return;
                    default:
                        System.out.println("Invalid option!");
                        break;
                }

                System.out.println();
            }
        } catch (SQLException e) {
            System.out.println("An error occurred in SQLTool: " + e.getMessage());
        }
    }

    private static boolean authenticateUser(Connection conn) throws SQLException {
        System.out.print("Enter the username: ");
        Scanner scanner = new Scanner(System.in);
        String username = scanner.nextLine();

        System.out.print("Enter the password: ");
        String password = scanner.nextLine();

        String query = "SELECT * FROM users WHERE username = ? AND password = ?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, username);
        stmt.setString(2, password);

        ResultSet resultSet = stmt.executeQuery();

        if (resultSet.next()) {
            System.out.println("Authentication successful!");
            return true;
        } else {
            System.out.println("Authentication failed. Incorrect username or password.");
            return false;
        }
    }

    private static void executeQuery(Connection conn) throws SQLException {
        System.out.print("Enter the SELECT query: ");
        Scanner scanner = new Scanner(System.in);
        String query = scanner.nextLine();

        PreparedStatement stmt = conn.prepareStatement(query);
        ResultSet resultSet = stmt.executeQuery();

        ResultSetMetaData metaData = resultSet.getMetaData();
        int columnCount = metaData.getColumnCount();

        while (resultSet.next()) {
            System.out.println("Result:");

            for (int i = 1; i <= columnCount; i++) {
                String columnName = metaData.getColumnName(i);
                String value = resultSet.getString(columnName);
                System.out.println(columnName + ": " + value);
            }

            System.out.println();
        }

        resultSet.close();
        stmt.close();
    }

    private static void executeInsert(Connection conn) throws SQLException {
        System.out.print("Enter the INSERT query: ");
        Scanner scanner = new Scanner(System.in);
        String insertQuery = scanner.nextLine();

        PreparedStatement stmt = conn.prepareStatement(insertQuery);
        int rowsAffected = stmt.executeUpdate();

        System.out.println(rowsAffected + " row(s) inserted successfully.");

        stmt.close();
    }

    private static void executeUpdate(Connection conn) throws SQLException {
        System.out.print("Enter the UPDATE query: ");
        Scanner scanner = new Scanner(System.in);
        String updateQuery = scanner.nextLine();

        PreparedStatement stmt = conn.prepareStatement(updateQuery);
        int rowsAffected = stmt.executeUpdate();

        System.out.println(rowsAffected + " row(s) updated successfully.");

        stmt.close();
    }

    private static void executeDelete(Connection conn) throws SQLException {
        System.out.print("Enter the DELETE query: ");
        Scanner scanner = new Scanner(System.in);
        String deleteQuery = scanner.nextLine();

        PreparedStatement stmt = conn.prepareStatement(deleteQuery);
        int rowsAffected = stmt.executeUpdate();

        System.out.println(rowsAffected + " row(s) deleted successfully.");

        stmt.close();
    }

    private static void generateReport(Connection conn) throws SQLException {
        System.out.println("Generating report...");

        String query = "SELECT * FROM sales";
        PreparedStatement stmt = conn.prepareStatement(query);
        ResultSet resultSet = stmt.executeQuery();

        StringBuilder reportContent = new StringBuilder();
        reportContent.append("Sales Report:\n\n");
        reportContent.append("Date\t\tProduct\t\tQuantity\tPrice\n");

        while (resultSet.next()) {
            String date = resultSet.getString("date");
            String product = resultSet.getString("product");
            int quantity = resultSet.getInt("quantity");
            double price = resultSet.getDouble("price");

            reportContent.append(date).append("\t");
            reportContent.append(product).append("\t\t");
            reportContent.append(quantity).append("\t\t");
            reportContent.append(price).append("\n");
        }

        resultSet.close();
        stmt.close();

        String fileName = "sales_report.txt";
        try (PrintWriter writer = new PrintWriter(fileName)) {
            writer.write(reportContent.toString());
        } catch (FileNotFoundException e) {
            System.out.println("Failed to save the report: " + e.getMessage());
            return;
        }

        System.out.println("Report generated successfully. Saved as " + fileName);
    }

    private static void testSQLInjection(Connection conn) throws SQLException {
        System.out.print("Enter the username: ");
        Scanner scanner = new Scanner(System.in);
        String username = scanner.nextLine();

        String query = "SELECT * FROM users WHERE username = '" + username + "'";
        PreparedStatement stmt = conn.prepareStatement(query);
        ResultSet resultSet = stmt.executeQuery();

        if (resultSet.next()) {
            System.out.println("User found:");
            System.out.println("Username: " + resultSet.getString("username"));
            System.out.println("Email: " + resultSet.getString("email"));
        } else {
            System.out.println("User not found.");
        }

        resultSet.close();
        stmt.close();
    }
}
