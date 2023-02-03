package components.menus;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class PageConnexion {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter pseudo: ");
        String pseudo = sc.nextLine();
        System.out.print("Enter password: ");
        String passwd = sc.nextLine();

        try (Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/istore", "root", "root")) {

            PreparedStatement stmt = connection.prepareStatement(
                    "SELECT * FROM user WHERE pseudo = ? AND password = ?");
            stmt.setString(1, pseudo);
            stmt.setString(2, passwd);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                System.out.println("Salut, " + rs.getString("id_role"));
            } else {
                System.out.println("Information fausse =/");
            }
        } catch (SQLException e) {
            System.err.println("Sa marche po ='(" + e.getMessage());
        }
    }
}
