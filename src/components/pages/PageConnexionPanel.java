package components.pages;

import components.fenetre.WindowScreen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PageConnexionPanel extends JPanel {

    private final int panelW;
    private final int panelH;
    private final JPanel pan;

    public PageConnexionPanel(int panelW, int panelH, JPanel pan) {
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

        userNameLabel.setBounds((int) (this.panelW*0.5) - widthComponent/2, (int) (this.panelH*0.25) - heightComponent/2, widthComponent, heightComponent);
        userNameField.setBounds((int) (this.panelW*0.6) - widthComponent/2, (int) (this.panelH*0.25) - heightComponent/2, widthComponent, heightComponent);

        passwordLabel.setBounds((int) (this.panelW*0.5) - widthComponent/2, (int) (this.panelH*0.5) - heightComponent/2, widthComponent, heightComponent);
        passwordField.setBounds((int) (this.panelW*0.6) - widthComponent/2, (int) (this.panelH*0.5) - heightComponent/2, widthComponent, heightComponent);

        submit.setBounds((int) (this.panelW*0.5) - widthComponent/2, (int) (this.panelH*0.75) - heightComponent/2, widthComponent, heightComponent);
        submit.addActionListener(event -> changePanel(pan, this.panelW, this.panelH, event));

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

    private static void changePanel(JPanel pan, int screenW, int screenH, ActionEvent event) {
        pan.removeAll();
        WindowScreen.pageInventory(pan, screenW, screenH);
        pan.updateUI();
    }
}
