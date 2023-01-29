package components.button;

import components.menus.PageInscription;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SquareButton {
    int width;
    int height;
    String text;
    JButton squareButton;
    public SquareButton(int width, int height, String text) {
        this.width = width;
        this.height = height;
        this.text = text;
        squareButton = new JButton(text);
        squareButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PageInscription test = new PageInscription(1000, 1000);
                test.hideInscriptionScreen();
            }
        });

    }
}
