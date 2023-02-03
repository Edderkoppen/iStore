package connexion;

import java.sql.*;

public class Connexion {

    public static void connexionBdd() {
        String BDD = "istore";
        String url = "jdbc:mysql://localhost:3306/" + BDD;
        String user = "root";
        String passwd = "root";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url, user, passwd);
            System.out.println("Connecté");

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from user;");
            rs.next();
//            System.out.println(rs.getString());

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Impossible de charger le Driver");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String args[]) {
        connexionBdd();
    }
}
