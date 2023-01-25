package components.menus;
import components.button.SquareButton;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PageInscription {

    int width;
    int height;

    JFrame frameInscription;
    public PageInscription(int height, int width) {
        this.height = height;
        this.width = width;
        frameInscription= new JFrame();

        frameInscription.setSize(this.width*3/4, this.height*3/4);
        frameInscription.setLocation((this.width-this.width*3/4)/2, (this.height-this.height*3/4)/2);
        frameInscription.setResizable(false);
        frameInscription.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public void draw() {
        frameInscription.setVisible(true);

        JButton squareButton = new JButton("coucou");
        squareButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frameInscription.hide();
            }
        });
        frameInscription.add(squareButton);
    }

    public void hide() {
        frameInscription.setVisible(false);
    }

}
