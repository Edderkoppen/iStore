package components.pages;

import components.fenetre.WindowScreen;
import connexion.DatabaseConnexion;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class InventoryPanel extends JPanel{
    private final int panelW;
    private final int panelH;

    public InventoryPanel(int panelW, int panelH) {
        super(null);
        this.panelW = panelW;
        this.panelH = panelH;

        int widthComponent = 100;
        int heightComponent = 30;


        ArrayList<String> informations = DatabaseConnexion.getStoreInventory(DatabaseConnexion.getNameStoreId(WindowScreen.userId));
        JLabel imageLabel = new JLabel();
        JLabel itemNameLabel = new JLabel("N/A");
        JLabel priceLabel = new JLabel("N/A");
        JLabel quantityLabel = new JLabel("N/A");


        if(!informations.isEmpty()) {
//            for(String infos : informations) {
                imageLabel.setIcon(new ImageIcon(informations.get(0)));
                itemNameLabel = new JLabel(informations.get(1));
                priceLabel = new JLabel(informations.get(2) + "€");
                quantityLabel = new JLabel(informations.get(3));
//            }
        }

        JButton increase = new JButton("+");
        JButton decrease = new JButton("-");

        //Position
        imageLabel.setBounds((int) (this.panelW*0.1) - widthComponent/2, (int) (this.panelH*0.5) - heightComponent/2, widthComponent/10, heightComponent);
        itemNameLabel.setBounds((int) (this.panelW*0.2) - widthComponent/2, (int) (this.panelH*0.5) - heightComponent/2, widthComponent, heightComponent);
        priceLabel.setBounds((int) (this.panelW*0.5) - widthComponent/2, (int) (this.panelH*0.5) - heightComponent/2, widthComponent/2, heightComponent);
//        increase.setBounds((int) (this.panelW*0.6) - widthComponent/2, (int) (this.panelH*0.5) - heightComponent/2, widthComponent, heightComponent);
        quantityLabel.setBounds((int) (this.panelW*0.8) - widthComponent/2, (int) (this.panelH*0.5) - heightComponent/2, widthComponent/2, heightComponent);
//        decrease.setBounds((int) (this.panelW*0.9) - widthComponent/2, (int) (this.panelH*0.5) - heightComponent/2, widthComponent, heightComponent);

        this.add(imageLabel);
        this.add(itemNameLabel);
        this.add(priceLabel);
//        this.add(increase);
        this.add(quantityLabel);
//        this.add(decrease);


    }
}
