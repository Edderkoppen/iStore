import components.menus.PageConnexion;
import components.menus.PageInscription;
import components.fenetre.WindowScreen;
import connexion.DatabaseConnexion;

import java.awt.Dimension;
import java.util.ArrayList;

//nameLabel.setBounds((int) ((this.screenW * 0.25) - this.labelW / 2), (int) (this.screenH*0.25 - this.labelH/2), this.labelW, this.labelH); //Bon calcul de positionnement.
//surnameLabel.setBounds((this.screenW/2)-(this.labelW/2), this.screenH/3, this.labelW, this.labelH);

public class Main {
    public static void main(String[] args) {

        Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        int width  = (int)dimension.getWidth();
        int height = (int)dimension.getHeight();

        DatabaseConnexion conn = new DatabaseConnexion();
        ArrayList<String> coucou = conn.querieInventory();
        WindowScreen window = new WindowScreen(width, height);
        window.setVisible(true);

        for (int i = 0; i<coucou.size(); i++ ) {
            System.out.println(coucou.get(i));
        }

    }
}