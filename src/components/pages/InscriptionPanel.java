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

        int widthComponent = 170;
        int heightComponent = 30;
        //E-MAIL
        JLabel emailLabel = new JLabel("E-mail :");
        emailLabel.setBounds((int) (this.panelW*0.45) - widthComponent/2, (int) (this.panelH*0.10) - heightComponent/2, widthComponent, heightComponent);
        this.add(emailLabel);
        JTextField emailField = new JTextField();
        emailField.setBounds((int) (this.panelW*0.55) - widthComponent/2, (int) (this.panelH*0.10) - heightComponent/2, widthComponent, heightComponent);
        this.add(emailField);

        //PASSWORD
        JLabel passwordLabel = new JLabel("Password :");
        passwordLabel.setBounds((int) (this.panelW*0.45) - widthComponent/2, (int) (this.panelH*0.20) - heightComponent/2, widthComponent, heightComponent);
        this.add(passwordLabel);
        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds((int) (this.panelW*0.55) - widthComponent/2, (int) (this.panelH*0.20) - heightComponent/2, widthComponent, heightComponent);
        this.add(passwordField);

        //PSEUDO
        JLabel pseudoLabel = new JLabel("Pseudo :");
        pseudoLabel.setBounds((int) (this.panelW*0.45) - widthComponent/2, (int) (this.panelH*0.30) - heightComponent/2, widthComponent, heightComponent);
        this.add(pseudoLabel);
        JPasswordField pseudoField = new JPasswordField();
        pseudoField.setBounds((int) (this.panelW*0.55) - widthComponent/2, (int) (this.panelH*0.30) - heightComponent/2, widthComponent, heightComponent);
        this.add(pseudoField);

        //FIRST NAME
        JLabel firstNameLabel = new JLabel("First Name :");
        firstNameLabel.setBounds((int) (this.panelW*0.45) - widthComponent/2, (int) (this.panelH*0.40) - heightComponent/2, widthComponent, heightComponent);
        this.add(firstNameLabel);
        JPasswordField firstNameField = new JPasswordField();
        firstNameField.setBounds((int) (this.panelW*0.55) - widthComponent/2, (int) (this.panelH*0.40) - heightComponent/2, widthComponent, heightComponent);
        this.add(firstNameField);


        //SURNAME
        JLabel surnameLabel = new JLabel("Surname :");
        surnameLabel.setBounds((int) (this.panelW*0.45) - widthComponent/2, (int) (this.panelH*0.50) - heightComponent/2, widthComponent, heightComponent);
        this.add(surnameLabel);
        JPasswordField surnameField = new JPasswordField();
        surnameField.setBounds((int) (this.panelW*0.55) - widthComponent/2, (int) (this.panelH*0.50) - heightComponent/2, widthComponent, heightComponent);
        this.add(surnameField);

        //SUBMIT
        JButton submit = new JButton("Se connecter");
        submit.setBounds((int) (this.panelW*0.5) - widthComponent/2, (int) (this.panelH*0.60) - heightComponent/2, widthComponent, heightComponent);
        this.add(submit);


        this.setBackground(Color.gray);
    }
}
