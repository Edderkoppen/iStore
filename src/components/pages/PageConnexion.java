package components.pages;

import javax.swing.*;
import java.awt.*;

public class PageConnexion extends JPanel {
    private JPanel createForm(JPanel pan) {


        int labelW = 150;
        int labelH = 100;
        int buttonW = 150;
        int buttonH = 100;

        JLabel nameLabel = new JLabel("Prénom :", SwingConstants.CENTER);
        JLabel surnameLabel = new JLabel("Nom :", SwingConstants.CENTER);
        JLabel emailLabel = new JLabel("Email :", SwingConstants.CENTER);
        JLabel passwordLabel = new JLabel("Mot de passe :", SwingConstants.CENTER);

        JTextField nameField = new JTextField();
        JTextField surnameField = new JTextField();
        JTextField emailField = new JTextField();
        JPasswordField passwordField = new JPasswordField();

        JButton submitButton = new JButton("Se connecter");

        nameLabel.setBounds(100, 50, labelW, labelH);
        nameField.setBounds(100, 70, labelW, labelH);

//        submitButton.setBounds(((this.screenW)/2)-(buttonW/2), (int) (this.screenH*0.5 - buttonH/2), buttonW, buttonH);

        pan.setLayout(null);
        pan.setBackground(Color.CYAN);
        pan.add(nameLabel);
        pan.add(nameField);
//        pan.add(submitButton);
//        pan.add(surnameLabel);
//        pan.add(surnameField);
//        pan.add(emailLabel);
//        pan.add(emailField);
//        pan.add(passwordLabel);
//        pan.add(passwordField);

        return pan;
    }

}
