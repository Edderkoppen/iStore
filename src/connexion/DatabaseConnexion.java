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

    public static ArrayList<String> querieStoreInfos() {
        String querie = "select s.store_name, u.first_name, u.surname from store s\n" +
                "inner join user u on s.id_store = u.id_store;";
        ArrayList<String> listResult = new ArrayList<>();

        try {
            Statement stmt = database.createStatement();
            ResultSet res = stmt.executeQuery(querie);

            while (res.next()) {
                listResult.add(res.getString("store_name"));
                listResult.add(res.getString("first_name") + " " + res.getString("surname"));

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);

        }

        return listResult;
    }


    public static ArrayList<String> querieStoreName() {
        String querie = "select store_name from store;";
        ArrayList<String> listResult = new ArrayList<>();

        try {
            Statement stmt = database.createStatement();
            ResultSet res = stmt.executeQuery(querie);

            while (res.next()) {
                listResult.add(res.getString("store_name"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);

        }

        return listResult;
    }

    public static ArrayList<String> querieInventory() {
        String querie = "select i.item_name, i.item_price, s.store_name, inv.quantity from item as i\n" +
                "join inventory as inv on i.id_item = inv.id_item\n" +
                "join store as s on inv.id_store = s.id_store\n" +
                "where s.id_store = 1;";
        ArrayList<String> listResult = new ArrayList<>();

        try {
            Statement stmt = database.createStatement();
            ResultSet res = stmt.executeQuery(querie);

            while (res.next()) {
                listResult.add(res.getString("store_name"));
                listResult.add(res.getString("item_name"));
                listResult.add(res.getString("item_price"));
                listResult.add(res.getString("quantity"));
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
