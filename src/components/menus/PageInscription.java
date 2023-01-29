package components.menus;

import javax.swing.*;
import java.awt.*;

public class PageInscription extends JFrame {
    private int screenW;
    private int screenH;
    private JLabel nameLabel, surnameLabel, emailLabel, passwordLabel;
    private JTextField nameField, surnameField, emailField;
    private JPasswordField passwordField;
    private JButton submitButton;


    public PageInscription(int height, int width) {
        this.screenH = height;
        this.screenW = width;
    }


    public void inscriptionScreen() {
        this.setTitle("IStore");
        this.setSize(this.screenW*3/4, this.screenH*3/4);
        this.setLocationRelativeTo(null); // this.setLocation((this.width-this.width*3/4)/2, (this.height-this.height*3/4)/2); Pour positionner la frame au centre de la page.
        this.setResizable(false);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        nameLabel = new JLabel("Prénom :");
        nameLabel.setBounds(((this.screenW*3/4)/2)-50, 200, 100, 200);

        surnameLabel = new JLabel("Nom :");
        emailLabel = new JLabel("Email :");
        passwordLabel = new JLabel("Mot de passe :");
        nameField = new JTextField();
        surnameField = new JTextField();
        emailField = new JTextField();
        passwordField = new JPasswordField();
        submitButton = new JButton("Soumettre");

        submitButton.addActionListener(click -> hideWindow());
        submitButton.setBounds(((this.screenW*3/4)/2)-50, 20, 100, 50);

        JPanel test = new JPanel();
        test.setLayout(null);
        test.setBackground(Color.BLUE);
        test.add(submitButton);
        test.add(nameLabel);
        this.add(test);
//        GridLayout layout = new GridLayout(5,2);
//        layout.setHgap(250);
//        layout.setVgap(50);
//        this.setLayout(layout);
//        this.add(nameLabel);
//        this.add(nameField);
//        this.add(surnameLabel);
//        this.add(surnameField);
//        this.add(emailLabel);
//        this.add(emailField);
//        this.add(passwordLabel);
//        this.add(passwordField);
//        this.add(submitButton);

        this.setVisible(true);
    }


    public void hideWindow() {
        this.setVisible(false);
    }

}
