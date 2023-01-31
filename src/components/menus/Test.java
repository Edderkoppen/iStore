package components.menus;

import javax.swing.*;
import java.awt.*;

public class Test extends JFrame {
    private final int screenW;
    private final int screenH;
    private final int labelW;
    private final int labelH;
    private final int buttonW;
    private final int buttonH;


    public Test(int height, int width) {
        this.screenH = height*3/4; //Taille de la Frame par rapport a la taille totale de l'écran.
        this.screenW = width*3/4;
        this.labelH = 100;
        this.labelW = 200;
        this.buttonH = 50;
        this.buttonW = 200;
    }


    public JPanel getPanel() {
        JLabel nameLabel = new JLabel("Prénom :", SwingConstants.CENTER);
        JLabel surnameLabel = new JLabel("Nom :", SwingConstants.CENTER);
        JLabel emailLabel = new JLabel("Email :", SwingConstants.CENTER);
        JLabel passwordLabel = new JLabel("Mot de passe :", SwingConstants.CENTER);

        JTextField nameField = new JTextField();
        JTextField surnameField = new JTextField();
        JTextField emailField = new JTextField();
        JPasswordField passwordField = new JPasswordField();

        JButton submitButton = new JButton("Page test");


        nameLabel.setFont(new Font("Serif", Font.BOLD, 30));
        nameLabel.setForeground(Color.black);
        nameLabel.setBackground(Color.GREEN);
        nameLabel.setOpaque(true);
        nameLabel.setBounds((int) ((this.screenW * 0.25) - this.labelW / 2), (int) (this.screenH*0.25 - this.labelH/2), this.labelW, this.labelH); //Bon calcul de positionnement.

        surnameLabel.setBounds((this.screenW/2)-(this.labelW/2), this.screenH/3, this.labelW, this.labelH);

        nameField.setBounds((int) ((this.screenW * 0.75) - this.labelW / 2), (int) (this.screenH*0.25 - this.labelH/2), this.labelW, this.labelH);

        submitButton.setBounds(((this.screenW)/2)-(this.buttonW/2), this.screenH/2, this.buttonW, this.buttonH);
//        submitButton.addActionListener(click -> hideInscriptionScreen());

        JPanel pan = new JPanel();
        pan.setLayout(null);
        pan.setBackground(Color.BLUE);
        pan.add(nameLabel);
        pan.add(submitButton);
        pan.add(nameField);

        return pan;
    }


    public void hide() {
        this.setVisible(false);
    }
}
