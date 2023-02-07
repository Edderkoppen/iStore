package components.pages;

import components.fenetre.WindowScreen;
import connexion.DatabaseConnexion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Arrays;

public class ConnexionPanel extends JPanel {

    private final int panelW;
    private final int panelH;
    private final JPanel pan;

    public ConnexionPanel(int panelW, int panelH, JPanel pan) {
        super(null);
        this.panelW = panelW;
        this.panelH = panelH;
        this.pan = pan;

        int widthComponent = 200;
        int heightComponent = 30;

        JLabel userNameLabel = new JLabel("Utilisateur :");
        JLabel passwordLabel = new JLabel("Mot de passe :");

        JTextField userNameField = new JTextField();
        JPasswordField passwordField = new JPasswordField();

        JButton submit = new JButton("Se connecter");

        userNameLabel.setBounds((int) (this.panelW*0.45) - widthComponent/2, (int) (this.panelH*0.25) - heightComponent/2, widthComponent, heightComponent);
        userNameField.setBounds((int) (this.panelW*0.55) - widthComponent/2, (int) (this.panelH*0.25) - heightComponent/2, widthComponent, heightComponent);

        passwordLabel.setBounds((int) (this.panelW*0.45) - widthComponent/2, (int) (this.panelH*0.5) - heightComponent/2, widthComponent, heightComponent);
        passwordField.setBounds((int) (this.panelW*0.55) - widthComponent/2, (int) (this.panelH*0.5) - heightComponent/2, widthComponent, heightComponent);

        submit.setBounds((int) (this.panelW*0.5) - widthComponent/2, (int) (this.panelH*0.75) - heightComponent/2, widthComponent, heightComponent);
        submit.addActionListener(event -> {
            String pseudo = DatabaseConnexion.getPseudo(userNameField.getText());
            String password = DatabaseConnexion.getPassword(new String(passwordField.getPassword()));

            if(pseudo != null && password != null && userNameField.getText().matches(pseudo) && new String(passwordField.getPassword()).matches(password)) {
                WindowScreen.userId =  setUserId(pseudo);
                changePanel(this.pan, this.panelW, this.panelH, event);
                System.out.println(WindowScreen.userId);
                System.out.println(pseudo);
                System.out.println(WindowScreen.userId);

            } else {
                JOptionPane.showMessageDialog(this.pan, "Nom d'utilisateur ou mot de passe incorrect", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        });

        this.add(userNameLabel);
        this.add(userNameField);
        this.add(passwordLabel);
        this.add(passwordField);
        this.add(submit);

        this.setBackground(Color.orange);
    }

//    private void testListener(ActionEvent event) {
//        System.out.println("cliqué");
//    }

    private String getEntry(JTextField username, ActionEvent event) {
//        username.getText();
        System.out.println(username.getText());
        return username.getText();

    }
    private static void changePanel(JPanel pan, int screenW, int screenH, ActionEvent event) {
        pan.removeAll();
        WindowScreen.pageInventory(pan, screenW, screenH);
        pan.updateUI();
    }

    public static int setUserId(String pseudo) {
        return DatabaseConnexion.getUserId(pseudo);
    }
}
