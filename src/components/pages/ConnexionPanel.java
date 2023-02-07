package components.pages;

import components.fenetre.WindowScreen;
import connexion.DatabaseConnexion;

import javax.swing.*;
import java.awt.*;

public class ConnexionPanel extends JPanel {

    private final int panelW;
    private final int panelH;
    private final JPanel pan;
    private JFrame frame;

    public ConnexionPanel(JFrame frame, int panelW, int panelH, JPanel pan) {
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

        userNameLabel.setBounds((int) (this.panelW*0.40) - widthComponent/2, (int) (this.panelH*0.40) - heightComponent/2, widthComponent, heightComponent);
        userNameField.setBounds((int) (this.panelW*0.55) - widthComponent/2, (int) (this.panelH*0.40) - heightComponent/2, widthComponent, heightComponent);

        passwordLabel.setBounds((int) (this.panelW*0.35) - widthComponent/2, (int) (this.panelH*0.50) - heightComponent/2, widthComponent, heightComponent);
        passwordField.setBounds((int) (this.panelW*0.55) - widthComponent/2, (int) (this.panelH*0.50) - heightComponent/2, widthComponent, heightComponent);

        submit.setBounds((int) (this.panelW*0.55) - widthComponent/2, (int) (this.panelH*0.60) - heightComponent/2, widthComponent, heightComponent);
        submit.addActionListener(event -> {
            String pseudo = DatabaseConnexion.getPseudo(userNameField.getText());
            String password = DatabaseConnexion.getPassword(new String(passwordField.getPassword()));

            if (pseudo != null && password != null && userNameField.getText().matches(pseudo) && new String(passwordField.getPassword()).matches(password)) {
                WindowScreen.userId =  DatabaseConnexion.getUserId(pseudo);
                WindowScreen.pageInventoryRedraw(this.frame, this.pan, this.panelW, this.panelH);

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
}
