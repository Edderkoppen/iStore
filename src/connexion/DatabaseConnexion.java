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

    public static String getEmail(String email) {
        String querie = "SELECT email FROM user where email like '" + email + "';";
        String result = null;

        try {
            Statement stmt = database.createStatement();
            ResultSet res = stmt.executeQuery(querie);
            if(res.next()) {
                result = res.getString("email");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return result;
    }


    public static String getPassword(String email) {
        String querie = "SELECT password FROM user where email like '" + email + "';";
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

    public static int getUserId(String email) {
        String querie = "SELECT id_user FROM user where email like '" + email + "';";
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
    public static ArrayList<String> getUserInfos(String first_name, String last_name) {
        String querie = "SELECT email, first_name, surname FROM user where first_name like '" + first_name + "' and surname like '" + last_name + "';";
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

    public static ArrayList<String> getUserInfosFromId(int id_user) {
        String querie = "SELECT email, first_name, surname FROM user where id_user =" + id_user + ";";
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

    public static ArrayList<String> getStoreInventory(String store) {
        String querie = "select i.item_name, i.item_price, iv.quantity from item i\n" +
                "join inventory iv on i.id_item = iv.id_item\n" +
                "join store s on iv.id_store = s.id_store\n" +
                "where s.store_name like '" + store + "';";
        ArrayList<String> listResult = new ArrayList<>();

        try {
            Statement stmt = database.createStatement();
            ResultSet res = stmt.executeQuery(querie);

            while (res.next()) {
                listResult.add(res.getString("item_name"));
                listResult.add(res.getString("item_price"));
                listResult.add(res.getString("quantity"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return listResult;
    }

    public static int getItemQuantity(String itemName) {
        String querie = "select i.quantity from item\n" +
                "join inventory i on item.id_item = i.id_item\n" +
                "where item_name like '" + itemName + "';";

        int result = 0;

        try {
            Statement stmt = database.createStatement();
            ResultSet res = stmt.executeQuery(querie);

            if(res.next()) {
                result = res.getInt("quantity");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return result;
    }
    public static void updateInventoryQuantity(int baseQuantity, int quantity, String itemName, String signe) {
        String querie = "update inventory\n" +
                "join item i on i.id_item = inventory.id_item\n" +
                "set quantity = " + baseQuantity + signe + quantity + "\n" +
                "where i.item_name like '" + itemName + "';";
        ArrayList<String> listResult = new ArrayList<>();

        if(signe.matches("\\+") || baseQuantity - quantity >= 0) {
            try {
                Statement stmt = database.createStatement();
                stmt.executeUpdate(querie);

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
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


    public static void insertNewUser(String email, String password, String pseudo, String firstName, String surname) {
        String querie = "insert into user (email, password, pseudo, first_name, surname, id_role, id_store)\n" +
                "value ('" + email + "', '" + password + "', '" + pseudo + "', '" + firstName + "', '" + surname + "', 2, null);";
        try {
            Statement stmt = database.createStatement();
            stmt.executeUpdate(querie);

        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
    }
    /**
     * Ferme la connection à la base de donnée.
     */
    public static void closeConn(){

        try {
            System.out.println("Connexion à la base en cours de fermeture ...");
            database.close();
            System.out.println("Connexion fermée");

        } catch(SQLException e) {
            System.out.println("La connexion n'a pas été fermée correctement ...");
        }
    }

}
