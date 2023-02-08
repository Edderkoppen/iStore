package components.pages;

import connexion.DatabaseConnexion;
import controller.PasswordController;
import controller.WhiteListController;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class InscriptionPanel extends JPanel {

    private final int panelW;
    private final int panelH;
    private final JPanel pan;

    public InscriptionPanel(int panelW, int panelH, JPanel pan) {
        super(null);
        this.panelW = panelW;
        this.panelH = panelH;
        this.pan = pan;

        int widthComponent = 150;
        int heightComponent = 30;

        //FIRST NAME
        JLabel firstNameLabel = new JLabel("Prénom :");
        firstNameLabel.setBounds((int) (this.panelW*0.40) - widthComponent/2, (int) (this.panelH*0.20) - heightComponent/2, widthComponent, heightComponent);
        this.add(firstNameLabel);
        JTextField firstNameField = new JTextField();
        firstNameField.setBounds((int) (this.panelW*0.55) - widthComponent/2, (int) (this.panelH*0.20) - heightComponent/2, widthComponent, heightComponent);
        this.add(firstNameField);

        //SURNAME
        JLabel surnameLabel = new JLabel("Nom :");
        surnameLabel.setBounds((int) (this.panelW*0.40) - widthComponent/2, (int) (this.panelH*0.30) - heightComponent/2, widthComponent, heightComponent);
        this.add(surnameLabel);
        JTextField surnameField = new JTextField();
        surnameField.setBounds((int) (this.panelW*0.55) - widthComponent/2, (int) (this.panelH*0.30) - heightComponent/2, widthComponent, heightComponent);
        this.add(surnameField);

        //PSEUDO
        JLabel pseudoLabel = new JLabel("Pseudo :");
        pseudoLabel.setBounds((int) (this.panelW*0.40) - widthComponent/2, (int) (this.panelH*0.40) - heightComponent/2, widthComponent, heightComponent);
        this.add(pseudoLabel);
        JTextField pseudoField = new JTextField();
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

        ArrayList<String> whiteListElements = WhiteListController.getElements();

        //SUBMIT
        JButton submit = new JButton("S'inscrire");
        submit.setBounds((int) (this.panelW*0.55) - widthComponent/2, (int) (this.panelH*0.80) - heightComponent/2, widthComponent, heightComponent);
        submit.addActionListener(event -> {

            if (firstNameField.getText().isEmpty() || surnameField.getText().isEmpty() || emailField.getText().isEmpty() ||
                        pseudoField.getText().isEmpty() || passwordField.getPassword().length == 0 || passwordConfirmedField.getPassword().length == 0) {
                JOptionPane.showMessageDialog(this.pan, "Un ou plusieurs champs ne sont pas renseignés", "Erreur", JOptionPane.ERROR_MESSAGE);

            } else if (!emailField.getText().matches("([a-zA-Z]{1,30}(.|)[a-zA-Z]{1,30}@[a-zA-Z]{1,30}.(com|fr))")) {
                JOptionPane.showMessageDialog(this.pan, "Mot de passe renseigné incorrect", "Erreur", JOptionPane.ERROR_MESSAGE);

            } else if(!whiteListElements.contains(emailField.getText())) {
                JOptionPane.showMessageDialog(this.pan, "Email non validé par l'administrateur", "Erreur", JOptionPane.ERROR_MESSAGE);

            } else if (!new String(passwordField.getPassword()).matches(new String(passwordConfirmedField.getPassword()))) {
                JOptionPane.showMessageDialog(this.pan, "Les deux mots de passes ne sont pas identiques", "Erreur", JOptionPane.ERROR_MESSAGE);

            } else {
                DatabaseConnexion.insertNewUser(emailField.getText(), PasswordController.hashPassword(new String(passwordField.getPassword())), pseudoField.getText(), firstNameField.getText(), surnameField.getText() );
                emailField.setText(null);
                passwordField.setText(null);
                pseudoField.setText(null);
                firstNameField.setText(null);
                surnameField.setText(null);
                passwordConfirmedField.setText(null);
                JOptionPane.showMessageDialog(this.pan, "Inscription effectuée avec succès", "Succès", JOptionPane.INFORMATION_MESSAGE);
                DatabaseConnexion.closeConn();
                new DatabaseConnexion();
            }
        });

        this.add(submit);

        this.setBackground(Color.gray);
    }
}
