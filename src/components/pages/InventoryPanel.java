package components.pages;

import javax.swing.*;
import java.awt.*;

public class InventoryPanel extends JPanel{
    private final int panelW;
    private final int panelH;

    public InventoryPanel(int panelW, int panelH) {
        super(null);
        this.panelW = panelW;
        this.panelH = panelH;

        int widthComponent = 50;
        int heightComponent = 30;


        JLabel imageLabel = new JLabel();
        imageLabel.setIcon(new ImageIcon("src/assets/icons/copy.png"));
        JLabel itemNameLabel = new JLabel("AirMax");
        JLabel priceLabel = new JLabel("190 €");
        JLabel quantityLabel = new JLabel("1");

        JButton increase = new JButton("+");
        JButton decrease = new JButton("-");

        //Position
        imageLabel.setBounds((int) (this.panelW*0.1) - widthComponent/2, (int) (this.panelH*0.5) - heightComponent/2, widthComponent, heightComponent);
        itemNameLabel.setBounds((int) (this.panelW*0.2) - widthComponent/2, (int) (this.panelH*0.5) - heightComponent/2, widthComponent, heightComponent);
        priceLabel.setBounds((int) (this.panelW*0.3) - widthComponent/2, (int) (this.panelH*0.5) - heightComponent/2, widthComponent, heightComponent);
        increase.setBounds((int) (this.panelW*0.4) - widthComponent/2, (int) (this.panelH*0.5) - heightComponent/2, widthComponent, heightComponent);
        quantityLabel.setBounds((int) (this.panelW*0.5) - widthComponent/2, (int) (this.panelH*0.5) - heightComponent/2, widthComponent, heightComponent);
        decrease.setBounds((int) (this.panelW*0.6) - widthComponent/2, (int) (this.panelH*0.5) - heightComponent/2, widthComponent, heightComponent);

        this.add(imageLabel);
        this.add(itemNameLabel);
        this.add(priceLabel);
        this.add(increase);
        this.add(quantityLabel);
        this.add(decrease);


    }
}
