package components.pages;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.reflect.Field;

public class UpdateUserPanel extends JPanel{
    private final int panelW;
    private final int panelH;
    private JFrame frame;
    public UpdateUserPanel(int panelW, int panelH) {
        super(null);
        this.panelW = panelW;
        this.panelH = panelH;

        int widthComponent = 150;
        int heightComponent = 30;

        //TEXTE
        JLabel infoLabel = new JLabel("Information utilisateur");
        JLabel emailLabel = new JLabel("email");
        JLabel passwordLabel = new JLabel("password");
        JLabel pseudoLabel = new JLabel("pseudo");
        JLabel first_nameLabel = new JLabel("first_name");
        JLabel surnameLabel = new JLabel("surname");
        JLabel passwordConfirmedLabel = new JLabel("Confirmation:");
        JButton submit = new JButton("Sauvegarder");

        //BUTTON
        JButton emailChange = new JButton("Modifier");
        JButton passwordChange = new JButton("Modifier");
        JButton passwordConfirmedChange = new JButton("Modifier");
        JButton pseudoChange = new JButton("Modifier");

        //FIELD
        JTextField emailField = new JTextField();
        JPasswordField passwordField = new JPasswordField();
        JPasswordField passwordConfirmedField = new JPasswordField();
        JPasswordField pseudoField = new JPasswordField();

        //POSITION LABEL
        infoLabel.setBounds((int) (this.panelW*0.5) - widthComponent/2, (int) (this.panelH*0.10) - heightComponent/2, widthComponent, heightComponent);
        first_nameLabel.setBounds((int) (this.panelW*0.4) - widthComponent/2, (int) (this.panelH*0.20) - heightComponent/2, widthComponent, heightComponent);
        surnameLabel.setBounds((int) (this.panelW*0.65) - widthComponent/2, (int) (this.panelH*0.20) - heightComponent/2, widthComponent, heightComponent);
        emailLabel.setBounds((int) (this.panelW*0.4) - widthComponent/2, (int) (this.panelH*0.30) - heightComponent/2, widthComponent, heightComponent);
        passwordLabel.setBounds((int) (this.panelW*0.4) - widthComponent/2, (int) (this.panelH*0.40) - heightComponent/2, widthComponent, heightComponent);
        passwordConfirmedLabel.setBounds((int) (this.panelW*0.4) - widthComponent/2, (int) (this.panelH*0.50) - heightComponent/2, widthComponent, heightComponent);
        pseudoLabel.setBounds((int) (this.panelW*0.4) - widthComponent/2, (int) (this.panelH*0.60) - heightComponent/2, widthComponent, heightComponent);
        submit.setBounds((int) (this.panelW*0.5) - widthComponent/2, (int) (this.panelH*0.80) - heightComponent/2, widthComponent, heightComponent);


        //POSITION CHANGE
        emailChange.setBounds((int) (this.panelW*0.6) - widthComponent/2, (int) (this.panelH*0.30) - heightComponent/2, widthComponent, heightComponent);
        passwordChange.setBounds((int) (this.panelW*0.6) - widthComponent/2, (int) (this.panelH*0.40) - heightComponent/2, widthComponent, heightComponent);
        passwordConfirmedChange.setBounds((int) (this.panelW*0.6) - widthComponent/2, (int) (this.panelH*0.50) - heightComponent/2, widthComponent, heightComponent);
        pseudoChange.setBounds((int) (this.panelW*0.6) - widthComponent/2, (int) (this.panelH*0.60) - heightComponent/2, widthComponent, heightComponent);

        //POSITION FIELD
        emailField.setBounds((int) (this.panelW*0.4) - widthComponent/2, (int) (this.panelH*0.30) - heightComponent/2, widthComponent, heightComponent);
        passwordField.setBounds((int) (this.panelW*0.4) - widthComponent/2, (int) (this.panelH*0.40) - heightComponent/2, widthComponent, heightComponent);
        passwordConfirmedField.setBounds((int) (this.panelW*0.4) - widthComponent/2, (int) (this.panelH*0.50) - heightComponent/2, widthComponent, heightComponent);
        pseudoField.setBounds((int) (this.panelW*0.4) - widthComponent/2, (int) (this.panelH*0.60) - heightComponent/2, widthComponent, heightComponent);

        this.add(submit);

        //LABEL
        this.add(infoLabel);
        this.add(emailLabel);
        this.add(passwordLabel);
        this.add(pseudoLabel);
        this.add(first_nameLabel);
        this.add(surnameLabel);
        this.add(passwordConfirmedLabel);

        //CHANGE
        this.add(passwordChange);
        this.add(passwordConfirmedChange);
        this.add(emailChange);
        this.add(pseudoChange);

        //FIELD
        this.add(passwordField);
        this.add(emailField);
        this.add(passwordConfirmedField);
        this.add(pseudoField);

        //INVISIBLE FILED
        emailField.setVisible(false);
        passwordField.setVisible(false);
        passwordConfirmedField.setVisible(false);
        pseudoField.setVisible(false);

        //BG COLOR
        this.setBackground(Color.gray);

        //CONDITION EMAIL
        emailChange.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Object source = e.getSource();
                if (source == emailChange) {
                    //EMAIL
                    emailLabel.setVisible(false);
                    emailField.setVisible(true);
                }
            }
        });

        //CONDITION PASSWORD
        passwordChange.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Object source = e.getSource();
                if (source == passwordChange) {
                    //PASSWORD
                    passwordLabel.setVisible(false);
                    passwordField.setVisible(true);
                }
            }
        });

        //CONDITION CONRFIMED PASSWORD
        passwordConfirmedChange.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Object source = e.getSource();
                if (source == passwordConfirmedChange) {
                    //PASSWORD
                    passwordConfirmedLabel.setVisible(false);
                    passwordConfirmedField.setVisible(true);
                }
            }
        });

        //CONDITION PSEUDO
        pseudoChange.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Object source = e.getSource();
                if (source == pseudoChange) {
                    //PASSWORD
                    pseudoLabel.setVisible(false);
                    pseudoField.setVisible(true);
                }
            }
        });

        //CONDITION PSEUDO
        submit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Object source = e.getSource();
                if (source == submit) {

                    //Email
                    emailLabel.setVisible(true);
                    emailField.setVisible(false);

                    //Password
                    passwordLabel.setVisible(true);
                    passwordField.setVisible(false);

                    //Confirmed Password
                    passwordConfirmedLabel.setVisible(true);
                    passwordConfirmedField.setVisible(false);

                    //Pseudo
                    pseudoLabel.setVisible(true);
                    pseudoField.setVisible(false);
                }
            }
        });
    }
}