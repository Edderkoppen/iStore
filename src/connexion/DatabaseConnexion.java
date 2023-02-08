package connexion;

import java.sql.*;
import java.util.ArrayList;

public class DatabaseConnexion {
    private static Connection database;
    private final String BDD;
    private final String url;
    private final String user;
    private final String password;
    public DatabaseConnexion() {
        this.BDD = "istore";
        this.url = "jdbc:mysql://localhost:3306/" + BDD;
        this.user = "root";
        this.password = "root";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            database = DriverManager.getConnection(url, user, password);
            System.out.println("Connecté à la base");

        } catch (ClassNotFoundException e) {
            System.out.println("Impossible de charger le Driver :");
            e.printStackTrace();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getPseudo(String username) {
        String querie = "SELECT pseudo FROM user where pseudo like '" + username + "';";
        String result = null;

        try {
            Statement stmt = database.createStatement();
            ResultSet res = stmt.executeQuery(querie);
            if(res.next()) {
                result = res.getString("pseudo");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return result;
    }


    public static String getPassword(String password) {
        String querie = "SELECT password FROM user where password like '" + password + "';";
        String result = null;

        try {
            Statement stmt = database.createStatement();
            ResultSet res = stmt.executeQuery(querie);
            if(res.next()) {
                result = res.getString("password");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return result;
    }

    public static int getUserId(String pseudo) {
        String querie = "SELECT id_user FROM user where pseudo like '" + pseudo + "';";
        int result = 0;

        try {
            Statement stmt = database.createStatement();
            ResultSet res = stmt.executeQuery(querie);
            if(res.next()) {
                result = res.getInt("id_user");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return result;
    }

    public static String getNameStoreId(int id) {
        String querie = "SELECT s.store_name FROM store s " +
                "join user u on u.id_store = s.id_store " +
                "where u.id_user =" + id + ";";
        String result = null;

        try {
            Statement stmt = database.createStatement();
            ResultSet res = stmt.executeQuery(querie);
            if(res.next()) {
                result = res.getString("store_name");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return result;
    }

    /**
     * Récupère les informations de l'utilisateur.
     *
     * @return une arrayList contenant l'email, le prénom et le nom de l'utilisateur.
     */
    public static ArrayList<String> getUserInfos() {
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
    public static ArrayList<String> getUserPrincipalInfos(int id) {
        String querie = "SELECT first_name, surname FROM user WHERE id_user = "+ id +";";
        ArrayList<String> listResult = new ArrayList<>();

        try {
            Statement stmt = database.createStatement();
            ResultSet res = stmt.executeQuery(querie);

            while (res.next()) {
                listResult.add(res.getString("first_name"));
                listResult.add(res.getString("surname"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return listResult;
    }



    /**
     * Récupère les noms des magasins, et les noms et prénoms des utilisateurs en ayant l'accès.
     *
     * @return une arrayList contenant toutes ces informations.
     */
    public static ArrayList<String> getStoreInfos(String store) {

        String querie = "select s.store_name, u.first_name, u.surname from store s\n" +
                        "inner join user u on s.id_store = u.id_store\n" +
                        "where s.store_name like '" + store +"';";

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


    /**
     * Récupère l'ensemble des noms des magasins de la base de donnée.
     *
     * @return une arrayList contenant l'ensemble des noms de magasin.
     */
    public static ArrayList<String> getStoreName() {
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


    /**
     * Récupère l'ensemble de l'inventaire d'un magasin donné.
     * @param storeName   nom du magasin ur lequel on recherche l'inventaire.
     *
     * @return une arrayList contenant le nom du magasin, le nom de l'item, son prix et sa quantité.
     */
    public static ArrayList<String> getInventory(String storeName) {
        String querie = "select i.item_name, i.item_price, s.store_name, inv.quantity from item as i\n" +
                "join inventory as inv on i.id_item = inv.id_item\n" +
                "join store as s on inv.id_store = s.id_store\n" +
                "where s.store_name like '" + storeName + "';";
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


    /**
     * Ferme la connection à la base de donnée.
     */
    public void closeConn(){

        try {
            System.out.println("Connexion à la base en cours de fermeture ...");
            database.close();
            System.out.println("Connexion fermée");

        } catch(SQLException e) {
            System.out.println("La connexion n'a pas été fermée correctement ...");
        }
    }

}
