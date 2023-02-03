package connexion;

import java.sql.*;
import java.util.ArrayList;

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

    public static ArrayList<String> testData() {
        String querie = "SELECT email, first_name, surname FROM user where first_name like 'mathieu';";
        ArrayList<String> listResult = new ArrayList<>();

        try {
            Statement stmt = database.createStatement();
            ResultSet res = stmt.executeQuery(querie);

            while (res.next()) {

                listResult.add(res.getString("email"));
                listResult.add(res.getString("first_name"));
                listResult.add(res.getString("surname"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);

        }

        return listResult;
    }

    public void closeConn(){

        try {
            database.close();

        } catch(SQLException e) {
            System.out.println("La connexion n'a pas été fermée correctement");
        }
    }

}
