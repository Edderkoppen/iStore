import components.menus.PageInscription;
import connexion.Connexion;
import components.menus.PageInscription;
import java.awt.Dimension;

// Code


public class Main {
    public static void main(String[] args) {
//        Connexion connexion = new Connexion();
//        connexion.connexionBdd();

        Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        int height = (int)dimension.getHeight();
        int width  = (int)dimension.getWidth();
        PageInscription test = new PageInscription(height, width);
        test.draw();
    }
}