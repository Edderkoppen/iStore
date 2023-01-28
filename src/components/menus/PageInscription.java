package components.menus;

import javax.swing.*;
import java.awt.*;

public class PageInscription extends JFrame {
    int width;
    int height;

    JButton squareButton = new JButton("Valider");
    JPanel pan = new JPanel();


    public PageInscription(int height, int width) {

        this.height = height;
        this.width = width;

    }

    public void draw() {
        this.setTitle("IStore");
        this.setSize(this.width*3/4, this.height*3/4);
        this.setLocationRelativeTo(null);
//        this.setLocation((this.width-this.width*3/4)/2, (this.height-this.height*3/4)/2);
        this.setResizable(false);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        squareButton.addActionListener(click -> hideWindow());
        pan.add(squareButton);
        this.setContentPane(pan);
        this.setVisible(true);
    }

    public void hideWindow() {
        this.setVisible(false);
    }

}
