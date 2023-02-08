package components.pages;

import javax.swing.*;
import java.awt.*;


public class DeleteCreateItemPanel extends JPanel {
    private final int panelW;
    private final int panelH;
    private JFrame frame;
    public DeleteCreateItemPanel(int panelW, int panelH) {
        super(null);
        this.panelW = panelW;
        this.panelH = panelH;

        int widthComponent = 150;
        int heightComponent = 30;

        JLabel itemNameLabel = new JLabel("Air Miaw");
        JLabel itemPriceLabel = new JLabel("10 €");
        JLabel itemInfoNameLabel = new JLabel("Modele");
        JLabel itemInfoPriceLabel = new JLabel("Prix");

        JButton deleteStoreButton = new JButton("Supprimer");
        JButton createStoreButton = new JButton("Ajouter");

        JTextField itemNameField = new JTextField();
        JTextField itemPriceField = new JTextField();


        itemNameLabel.setBounds((int) (this.panelW*0.2) - widthComponent/2, (int) (this.panelH*0.30) - heightComponent/2, widthComponent, heightComponent);
        itemPriceLabel.setBounds((int) (this.panelW*0.35) - widthComponent/2, (int) (this.panelH*0.30) - heightComponent/2, widthComponent, heightComponent);
        itemInfoNameLabel.setBounds((int) (this.panelW*0.2) - widthComponent/2, (int) (this.panelH*0.15) - heightComponent/2, widthComponent, heightComponent);
        itemInfoPriceLabel.setBounds((int) (this.panelW*0.35) - widthComponent/2, (int) (this.panelH*0.15) - heightComponent/2, widthComponent, heightComponent);

        deleteStoreButton.setBounds((int) (this.panelW*0.5) - widthComponent/2, (int) (this.panelH*0.30) - heightComponent/2, widthComponent, heightComponent);
        createStoreButton.setBounds((int) (this.panelW*0.5) - widthComponent/2, (int) (this.panelH*0.70) - heightComponent/2, widthComponent, heightComponent);

        itemNameField.setBounds((int) (this.panelW*0.2) - widthComponent/2, (int) (this.panelH*0.70) - heightComponent/2, widthComponent, heightComponent);
        itemPriceField.setBounds((int) (this.panelW*0.35) - widthComponent/2, (int) (this.panelH*0.70) - heightComponent/2, widthComponent, heightComponent);

        this.add(itemNameLabel);
        this.add(itemPriceLabel);
        this.add(itemInfoNameLabel);
        this.add(itemInfoPriceLabel);
        this.add(deleteStoreButton);
        this.add(createStoreButton);
        this.add(itemNameField);
        this.add(itemPriceField);
    }
}