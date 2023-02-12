package istore.components.pages;

import istore.components.screen.WindowScreen;
import istore.controller.DatabaseConnexionController;
import istore.controller.PasswordController;

import javax.swing.*;
import java.awt.*;

public class ConnexionPage extends JPanel {

    private final int panelW;
    private final int panelH;
    private final JPanel pan;
    private final JFrame frame;

    public ConnexionPage(JFrame frame, int panelW, int panelH, JPanel pan) {
        super(null);
        this.panelW = panelW;
        this.panelH = panelH;
        this.pan = pan;
        this.frame = frame;

        int widthComponent = 150;
        int heightComponent = 30;

        JLabel userNameLabel = new JLabel("Utilisateur :");
        JLabel passwordLabel = new JLabel("Mot de passe :");

        JTextField userNameField = new JTextField();
        JPasswordField passwordField = new JPasswordField();

        JButton submit = new JButton("Se connecter");

        userNameLabel.setBounds((int) (this.panelW*0.25) - widthComponent/2, (int) (this.panelH*0.60) - heightComponent/2, widthComponent, heightComponent);
        userNameField.setBounds((int) (this.panelW*0.60) - widthComponent/2, (int) (this.panelH*0.60) - heightComponent/2, widthComponent, heightComponent);

        passwordLabel.setBounds((int) (this.panelW*0.25) - widthComponent/2, (int) (this.panelH*0.80) - heightComponent/2, widthComponent, heightComponent);
        passwordField.setBounds((int) (this.panelW*0.60) - widthComponent/2, (int) (this.panelH*0.80) - heightComponent/2, widthComponent, heightComponent);

        submit.setBounds((int) (this.panelW*0.60) - widthComponent/2, this.panelH - heightComponent/2, widthComponent, heightComponent);
        submit.addActionListener(event -> {
            String email = DatabaseConnexionController.getEmail(userNameField.getText());

            if (email != null && userNameField.getText().matches(email)) {
                String hashPswd = PasswordController.hashPassword(new String(passwordField.getPassword()));
                String databasePswd = DatabaseConnexionController.getPassword(email);

                if(hashPswd.matches(databasePswd)) {
                    WindowScreen.userId = DatabaseConnexionController.getUserId(email);
                    WindowScreen.pageInventoryRedraw(this.frame, this.pan, this.panelW, this.panelH);

                } else {
                    JOptionPane.showMessageDialog(this.pan, "Nom d'utilisateur ou mot de passe incorrect", "Erreur", JOptionPane.ERROR_MESSAGE);
                }

            } else {
                JOptionPane.showMessageDialog(this.pan, "Nom d'utilisateur ou mot de passe incorrect", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        });

        this.add(userNameLabel);
        this.add(userNameField);
        this.add(passwordLabel);
        this.add(passwordField);
        this.add(submit);

        this.setBackground(Color.gray);
    }
}
