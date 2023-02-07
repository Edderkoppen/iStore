package components.pages;

import javax.swing.*;
import java.awt.*;

public class InscriptionPanel extends JPanel {

    private final int panelW;
    private final int panelH;

    public InscriptionPanel(int panelW, int panelH) {
        super(null);
        this.panelW = panelW;
        this.panelH = panelH;


        int widthComponent = 150;
        int heightComponent = 30;

        //FIRST NAME
        JLabel firstNameLabel = new JLabel("Prénom :");
        firstNameLabel.setBounds((int) (this.panelW*0.40) - widthComponent/2, (int) (this.panelH*0.20) - heightComponent/2, widthComponent, heightComponent);
        this.add(firstNameLabel);
        JPasswordField firstNameField = new JPasswordField();
        firstNameField.setBounds((int) (this.panelW*0.55) - widthComponent/2, (int) (this.panelH*0.20) - heightComponent/2, widthComponent, heightComponent);
        this.add(firstNameField);

        //SURNAME
        JLabel surnameLabel = new JLabel("Nom :");
        surnameLabel.setBounds((int) (this.panelW*0.40) - widthComponent/2, (int) (this.panelH*0.30) - heightComponent/2, widthComponent, heightComponent);
        this.add(surnameLabel);
        JPasswordField surnameField = new JPasswordField();
        surnameField.setBounds((int) (this.panelW*0.55) - widthComponent/2, (int) (this.panelH*0.30) - heightComponent/2, widthComponent, heightComponent);
        this.add(surnameField);

        //PSEUDO
        JLabel pseudoLabel = new JLabel("Pseudo :");
        pseudoLabel.setBounds((int) (this.panelW*0.40) - widthComponent/2, (int) (this.panelH*0.40) - heightComponent/2, widthComponent, heightComponent);
        this.add(pseudoLabel);
        JPasswordField pseudoField = new JPasswordField();
        pseudoField.setBounds((int) (this.panelW*0.55) - widthComponent/2, (int) (this.panelH*0.40) - heightComponent/2, widthComponent, heightComponent);
        this.add(pseudoField);

        //E-MAIL
        JLabel emailLabel = new JLabel("E-mail :");
        emailLabel.setBounds((int) (this.panelW*0.40) - widthComponent/2, (int) (this.panelH*0.50) - heightComponent/2, widthComponent, heightComponent);
        this.add(emailLabel);
        JTextField emailField = new JTextField();
        emailField.setBounds((int) (this.panelW*0.55) - widthComponent/2, (int) (this.panelH*0.50) - heightComponent/2, widthComponent, heightComponent);
        this.add(emailField);

        //PASSWORD
        JLabel passwordLabel = new JLabel("Mot de passe :");
        passwordLabel.setBounds((int) (this.panelW*0.35) - widthComponent/2, (int) (this.panelH*0.60) - heightComponent/2, widthComponent, heightComponent);
        this.add(passwordLabel);
        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds((int) (this.panelW*0.55) - widthComponent/2, (int) (this.panelH*0.60) - heightComponent/2, widthComponent, heightComponent);
        this.add(passwordField);

        //PASSWORD CONFIRMED
        JLabel passwordConfirmedLabel = new JLabel("Confirmation:");
        passwordConfirmedLabel.setBounds((int) (this.panelW*0.35) - widthComponent/2, (int) (this.panelH*0.70) - heightComponent/2, widthComponent, heightComponent);
        this.add(passwordConfirmedLabel);
        JPasswordField passwordConfirmedField = new JPasswordField();
        passwordConfirmedField.setBounds((int) (this.panelW*0.55) - widthComponent/2, (int) (this.panelH*0.70) - heightComponent/2, widthComponent, heightComponent);
        this.add(passwordConfirmedField);


        //SUBMIT
        JButton submit = new JButton("S'inscrire");
        submit.setBounds((int) (this.panelW*0.55) - widthComponent/2, (int) (this.panelH*0.80) - heightComponent/2, widthComponent, heightComponent);
        this.add(submit);

        this.setBackground(Color.gray);
    }
}