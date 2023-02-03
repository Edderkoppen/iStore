package connexion;

import java.sql.*;

public class DatabaseConnexion {
    private static Connection database;
    public DatabaseConnexion() {
        String BDD = "istore";
        String url = "jdbc:mysql://localhost:3306/" + BDD;
        String user = "root";
        String passwd = "root";

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            this.database = DriverManager.getConnection(url, user, passwd);
            System.out.println("Connecté à la base");

        } catch (ClassNotFoundException e) {
            System.out.println("Impossible de charger le Driver :");
            e.printStackTrace();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static ResultSet testData() {

        ResultSet queryResult;

        try {
            Statement stmt = database.createStatement();
            queryResult = stmt.executeQuery("select * from user;");
            queryResult.next();

        } catch (SQLException e) {
            throw new RuntimeException(e);

        }

        return queryResult;
    }

}
