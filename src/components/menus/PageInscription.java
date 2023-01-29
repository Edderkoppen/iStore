package components.menus;

import javax.swing.*;
import java.awt.*;

public class PageInscription extends JFrame {
    private int screenW;
    private int screenH;
    private int labelW;
    private int labelH;
    private int buttonW;
    private int buttonH;
    private JLabel nameLabel, surnameLabel, emailLabel, passwordLabel;
    private JTextField nameField, surnameField, emailField;
    private JPasswordField passwordField;
    private JButton submitButton;


    public PageInscription(int height, int width) {
        this.screenH = height*3/4; //Taille de la Frame par rapport a la taille totale de l'écran.
        this.screenW = width*3/4;
        this.labelH = 100;
        this.labelW = 200;
        this.buttonH = 50;
        this.buttonW = 200;
    }


    public void inscriptionScreen() {
        this.setTitle("IStore");
        this.setSize(this.screenW, this.screenH);
        this.setLocationRelativeTo(null); // this.setLocation((this.width-this.width*3/4)/2, (this.height-this.height*3/4)/2); Pour positionner la frame au centre de la page.
        this.setResizable(false);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        nameLabel = new JLabel("Prénom :", SwingConstants.CENTER);
        surnameLabel = new JLabel("Nom :", SwingConstants.CENTER);
        emailLabel = new JLabel("Email :", SwingConstants.CENTER);
        passwordLabel = new JLabel("Mot de passe :", SwingConstants.CENTER);

        nameField = new JTextField();
        surnameField = new JTextField();
        emailField = new JTextField();
        passwordField = new JPasswordField();

        submitButton = new JButton("Soumettre");


        nameLabel.setFont(new Font("Serif", Font.BOLD, 30));
        nameLabel.setForeground(Color.black);
        nameLabel.setBounds(((this.screenW)/2)-200, this.screenH/8, this.labelW, this.labelH);

        surnameLabel.setBounds((this.screenW/2)-(this.labelW/2), this.screenH/3, this.labelW, this.labelH); // nope

        nameField.setBounds((this.screenW/2), this.screenH/8, this.labelW, 40);

        submitButton.setBounds(((this.screenW)/2)-(this.buttonW/2), this.screenH/2, this.buttonW, this.buttonH);
        submitButton.addActionListener(click -> hideInscriptionScreen());

        JPanel pan = new JPanel();
        pan.setLayout(null);
        pan.setBackground(Color.BLUE);
        pan.add(nameLabel);
        pan.add(submitButton);
        pan.add(nameField);

        this.add(pan);
        this.setVisible(true);
    }


    public void hideInscriptionScreen() {
        this.setVisible(false);
    }

}
