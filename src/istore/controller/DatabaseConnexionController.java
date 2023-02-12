package istore.controller;

import java.sql.*;
import java.util.ArrayList;

public class DatabaseConnexionController {
    private static Connection database;
    private final String BDD;
    private final String url;
    private final String user;
    private final String password;
    public DatabaseConnexionController() {
        this.BDD = "istore";
        this.url = "jdbc:mysql://localhost:3306/" + BDD;
        this.user = "root";
        this.password = "";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            database = DriverManager.getConnection(this.url, this.user, this.password);
            System.out.println("Connecté à la base");

        } catch (ClassNotFoundException e) {
            System.out.println("Impossible de charger le Driver :");
            e.printStackTrace();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * Récupère l'email d'un utilisateur.
     * @param email     email a récupérer.
     * @return l'email de l'utilisateur.
     */
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


    /**
     * Récupère le role de l'utilisateur.
     * @param id    id de l'utilisateur.
     * @return l'id du role de l'utilisateur.
     */
    public static int getRoleFromId(int id) {
        String querie = "SELECT id_role FROM user where id_user = " + id + ";";
        int result = 0;

        try {
            Statement stmt = database.createStatement();
            ResultSet res = stmt.executeQuery(querie);
            if(res.next()) {
                result = res.getInt("id_role");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }


    /**
     * Récupère le mot de passe de l'utilisateur.
     * @param email     email de l'utilisateur.
     * @return le mot de passe de l'utilisateur.
     */
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


    /**
     * Récupère l'id de l'utilisateur.
     * @param email     email de l'utilisateur.
     * @return l'id de l'utilisateur.
     */
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


    /**
     * Récupère le nom du magasin associé a un utilisateur.
     * @param id    id de l'utilisateur.
     * @return le nom du magsin associé.
     */
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
     * @param first_name    prénom de l'utilisateur.
     * @param last_name     nom de l'utilisateur
     * @return une arrayList contenant l'email, le prénom et le nom de l'utilisateur.
     */
    public static ArrayList<String> getUserInfos(String first_name, String last_name) {
        String querie = "SELECT email, first_name, surname, pseudo FROM user where first_name like '" + first_name + "' and surname like '" + last_name + "';";
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


    /**
     * Récupère les informations d'un utilisateur.
     * @param id_user   id de l'utilisateur.
     * @return les informations de l'utilisateur.
     */
    public static ArrayList<String> getUserInfosFromId(int id_user) {
        String querie = "SELECT email, first_name, surname, pseudo FROM user where id_user =" + id_user + ";";
        ArrayList<String> listResult = new ArrayList<>();

        try {
            Statement stmt = database.createStatement();
            ResultSet res = stmt.executeQuery(querie);

            while (res.next()) {
                listResult.add(res.getString("email"));
                listResult.add(res.getString("first_name"));
                listResult.add(res.getString("surname"));
                listResult.add(res.getString("pseudo"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listResult;
    }


    /**
     * Récupère l'inventaire d'un magasin.
     * @param store     nom du magasin.
     * @return les informations d'inventaire du magasin.
     */
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


    /**
     * Récupère la quantité en stock d'une marchandise.
     * @param itemName  nom de la marchandise.
     * @return la quantité en stock de la marchandise.
     */
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


    /**
     * Met a jour le contenu d'un inventaire.
     * @param baseQuantity      quantité initiale en stock.
     * @param quantity          quantité à ajouter ou retirer.
     * @param itemName          nom de la marchandise a modifier.
     * @param signe             + ou -.
     */
    public static void updateInventoryQuantity(int baseQuantity, int quantity, String itemName, String signe) {
        String querie = "update inventory\n" +
                "join item i on i.id_item = inventory.id_item\n" +
                "set quantity = " + baseQuantity + signe + quantity + "\n" +
                "where i.item_name like '" + itemName + "';";

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
     * Supprime un utilisateur.
     * @param id    id de l'utilisateur a supprimer.
     */
    public static void deleteUser(int id) {
        String querie = "delete from user\n" +
                        "where id_user = " + id + ";";

        try {
            Statement stmt = database.createStatement();
            stmt.executeUpdate(querie);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * Supprimer un utilisateur selon son email.
     * @param email     email de l'utilisateur a supprimer.
     */
    public static void deleteUserFromEmail(String email) {
        String querie = "delete from user\n" +
                "where email like '" + email + "';";

        try {
            Statement stmt = database.createStatement();
            stmt.executeUpdate(querie);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * Récupère l'id d'un utilisateur selon son prenom.
     * @param firstName     prenom de l'utilisateur.
     * @return l'id de l'utilisateur.
     */
    public static int getIdFromName(String firstName)  {
        String querie = "SELECT id_user FROM user where user.first_name like '" + firstName + "';";
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


    /**
     * Récupère le nom d'un magasin (verification d'existence).
     * @param store nom du magasin.
     * @return le nom du magasins s'il existe. Null sinon.
     */
    public static String getStore(String store) {
        String querie = "SELECT store_name FROM store where store.store_name like '" + store + "';";
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
     * Récupère le nom du magasin ainsi que les noms et prénoms des employés qui y sont assignés.
     * @return l'ensemble de ces informations dans une arraylist.
     */
    public static ArrayList<String> getAllStoreInfos() {

        String querie = "select s.store_name, u.first_name, u.surname from store s\n" +
                "inner join user u on s.id_store = u.id_store\n" +
                "order by s.store_name;";

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
     * Récupère les noms des magasins, et les noms et prénoms des utilisateurs en ayant l'accès.
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
     * Met a jour l'atttribution d'un magasin d'un utilisateur.
     * @param store     id du magasin.
     * @param email     email de l'utilisateur.
     */
    public static void updateStoreAttribution(int store, String email) {
        String querie = "update user\n" +
                "set id_store = " + store + "\n" +
                "where email like '" + email + "';";

        try {
            Statement stmt = database.createStatement();
            stmt.executeUpdate(querie);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * Met a jour le mot de passe d'un utilisateur.
     * @param newPassword   nouveau mot de passe.
     * @param id            id de l'utilisateur.
     */
    public static void updatePassword(String newPassword, int id) {
        String querie = "update user\n" +
                "set password = " + newPassword + "\n" +
                "where id_user = " + id + ";";

        try {
            Statement stmt = database.createStatement();
            stmt.executeUpdate(querie);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * Met à jour l'adresse email d'un utilisateur.
     * @param email     nouvel email de l'utilisateur.
     * @param id        id de l'utilisateur.
     */
    public static void updateEmail(String email, int id) {
        String querie = "update user\n" +
                "set email = " + email + "\n" +
                "where id_user = " + id + ";";

        try {
            Statement stmt = database.createStatement();
            stmt.executeUpdate(querie);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * Récupère l'id d'un magasin selon son nom.
     * @param storeName     nom du magasin.
     * @return l'id du magasin.
     */
    public static int getStoreId(String storeName) {
        String querie = "select id_store from store\n" +
                "where store.store_name like '" + storeName + "';";
        int result = 0;

        try {
            Statement stmt = database.createStatement();
            ResultSet res = stmt.executeQuery(querie);

            if(res.next()) {
                result = res.getInt("id_store");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }


    /**
     * Récupère l'id d'une marchandise selon son nom.
     * @param itemName  nom de la marchandise.
     * @return l'id de la marchandise.
     */
    public static int getItemId(String itemName) {
        String querie = "select id_item from item\n" +
                "where item.item_name like '" + itemName + "';";
        int result = 0;

        try {
            Statement stmt = database.createStatement();
            ResultSet res = stmt.executeQuery(querie);

            if(res.next()) {
                result = res.getInt("id_item");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }


    /**
     * Récupère le nom de la marchandise (vérification d'existance).
     * @param itemName  nom de la marchandise.
     * @return le nom de la marchandise si elle existe. Null sinon.
     */
    public static String getItem(String itemName) {
        String querie = "select item_name from item\n" +
                "where item_name like '" + itemName + "';";
        String result = null;

        try {
            Statement stmt = database.createStatement();
            ResultSet res = stmt.executeQuery(querie);

            if(res.next()) {
                result = res.getString("item_name");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }


    /**
     * Supprime une marchandise.
     * @param itemName  nom de la marchandise.
     */
    public static void deleteItem(String itemName) {
        String querie = "delete from item\n" +
                "where item_name like '" + itemName + "';";
        try {
            Statement stmt = database.createStatement();
            stmt.executeUpdate(querie);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * Supprime une marchandise de l'inventaire.
     * @param itemName  nom de la marchandise.
     */
    public static void deleteItemFromInventory(String itemName) {
        String querie = "delete from inventory\n" +
                "where id_item =\n" +
                "(select id_item from item\n" +
                "where item_name like '" + itemName + "');";
        try {
            Statement stmt = database.createStatement();
            stmt.executeUpdate(querie);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * Supprime un magasin.
     * @param storeName nom du magasin à supprimer.
     */
    public static void deleteStore(String storeName) {
        String querie = "delete from store\n" +
                "where store_name like '" + storeName + "';";
        try {
            Statement stmt = database.createStatement();
            stmt.executeUpdate(querie);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * Supprime un magasin de l'inventaire.
     * @param storeName nom du magasin a supprimer.
     */
    public static void deleteStoreFromInventory(String storeName) {
        String querie = "delete from inventory\n" +
                "where id_store =\n" +
                "(select id_store from store\n" +
                "where store_name like '" + storeName + "');";
        try {
            Statement stmt = database.createStatement();
            stmt.executeUpdate(querie);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * Met a jour l'inventaire en créant une nouvelle association magasin - marchandise.
     * @param store         id du magasin.
     * @param item          id de la marchandise.
     * @param quantity      quantité en stock disponible.
     */
    public static void updateInventory(int store, int item, int quantity) {
        String querie = "insert into inventory (id_store, id_item, quantity) \n" +
                "value (" + store + ", " + item + ", " + quantity + ");";
        try {
            Statement stmt = database.createStatement();
            stmt.executeUpdate(querie);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * Met a jour le pseudo d'un utilisateur.
     * @param pseudo    nouveau pseudo de l'utilisateur.
     * @param id        id de l'utilisateur.
     */
    public static void updatePseudo(String pseudo, int id) {
        String querie = "update user\n" +
                "set pseudo = '" + pseudo + "'\n" +
                "where id_user = " + id + ";";

        try {
            Statement stmt = database.createStatement();
            stmt.executeUpdate(querie);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * Crée un nouvel utilisateur.
     * @param email         email du nouvel utilisateur.
     * @param password      mot de passe du nouvel utilisateur.
     * @param pseudo        pseudo du nouvel utilisateur.
     * @param firstName     prenom du nouvel utilisateur.
     * @param surname       nom du nouvel utilisateur.
     */
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
     *Creation d'un nouveau magasin.
     * @param storeName     nom du nouveau magasin.
     */
    public static void createStore(String storeName) {
        String querie = "insert into store (store_name)\n" +
                "value ('" + storeName + "');";

        try {
            Statement stmt = database.createStatement();
            stmt.executeUpdate(querie);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * Creation d'une nouvelle marchandise.
     * @param itemName      nom de la marchandise.
     * @param itemPrice     prix de la marchandise.
     */
    public static void createItem(String itemName, double itemPrice) {
        String querie = "insert into item (item_name, item_price)\n" +
                "value ('" + itemName + "', " + itemPrice + ");";

        try {
            Statement stmt = database.createStatement();
            stmt.executeUpdate(querie);

        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
    }


    /**
     * Ferme la connection à la base de données.
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
