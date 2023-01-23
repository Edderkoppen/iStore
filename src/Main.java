import java.sql.*;

public class Main {
    public static void main(String[] args) {
        String BDD = "istore";
        String url = "jdbc:mysql://localhost:3306/istore";
        String user = "root";
        String passwd = "";

        try {

            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url, user, passwd);
            System.out.println("Connecté");

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("use istore;");

        } catch (ClassNotFoundException e) {
            System.out.println("Impossible de charger le Driver");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}