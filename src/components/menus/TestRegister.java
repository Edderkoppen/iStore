package components.menus;

import java.sql.*;
import java.util.Scanner;

public class TestRegister {

    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/istore";
    static final String USER = "root";
    static final String PASS = "root";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Connection conn = null;
        PreparedStatement stmt = null;

        System.out.print("Enter email: ");
        String email = scanner.nextLine();

        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        System.out.print("Enter pseudo: ");
        String pseudo = scanner.nextLine();

        System.out.print("Enter first name: ");
        String firstName = scanner.nextLine();

        System.out.print("Enter surname: ");
        String surname = scanner.nextLine();

        System.out.print("Enter id_role: ");
        int idRole = scanner.nextInt();

        try {
            Class.forName(JDBC_DRIVER);

            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            System.out.println("Inserting a new user...");
            stmt = conn.prepareStatement("INSERT INTO user (email, password, pseudo, first_name, surname, id_role) VALUES (?, ?, ?, ?, ?, ?)");
            stmt.setString(1, email);
            stmt.setString(2, password);
            stmt.setString(3, pseudo);
            stmt.setString(4, firstName);
            stmt.setString(5, surname);
            stmt.setInt(6, idRole);

            stmt.executeUpdate();
            System.out.println("User inserted successfully...");
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }
}

