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
        imageLabel.setBounds((int) (this.panelW*0.40) - widthComponent/2, (int) (this.panelH*0.5) - heightComponent/2, widthComponent, heightComponent);
        itemNameLabel.setBounds((int) (this.panelW*0.45) - widthComponent/2, (int) (this.panelH*0.5) - heightComponent/2, widthComponent, heightComponent);
        priceLabel.setBounds((int) (this.panelW*0.50) - widthComponent/2, (int) (this.panelH*0.5) - heightComponent/2, widthComponent, heightComponent);
        increase.setBounds((int) (this.panelW*0.55) - widthComponent/2, (int) (this.panelH*0.5) - heightComponent/2, widthComponent, heightComponent);
        quantityLabel.setBounds((int) (this.panelW*0.60) - widthComponent/2, (int) (this.panelH*0.5) - heightComponent/2, widthComponent, heightComponent);
        decrease.setBounds((int) (this.panelW*0.65) - widthComponent/2, (int) (this.panelH*0.5) - heightComponent/2, widthComponent, heightComponent);

        this.add(imageLabel);
        this.add(itemNameLabel);
        this.add(priceLabel);
        this.add(increase);
        this.add(quantityLabel);
        this.add(decrease);
    }
    public static void main(String[] args) {
        JFrame frame = new JFrame();

        Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        int panelW  = (int)dimension.getWidth();
        int panelH = (int)dimension.getHeight();

        frame.setSize(panelW*3/4, panelH*3/4);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);


        InventoryPanel main = new InventoryPanel(panelW*3/4,panelH*3/4);
        frame.add(main);
        frame.setVisible(true);
    }
}
