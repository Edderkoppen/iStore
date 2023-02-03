import components.menus.PageConnexion;
import components.menus.PageInscription;
import components.fenetre.WindowScreen;
import connexion.DatabaseConnexion;

import javax.xml.crypto.Data;
import java.awt.Dimension;

public class Main {
    public static void main(String[] args) {

        Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        int width  = (int)dimension.getWidth();
        int height = (int)dimension.getHeight();

        DatabaseConnexion conn = new DatabaseConnexion();
        WindowScreen window = new WindowScreen(width, height);
        window.setVisible(true);

        boolean fini = true;

        if(!fini) {
            conn.closeConn();
            System.out.println("Connection fermée");
        }
    }
}