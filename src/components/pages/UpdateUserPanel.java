package components.pages;

import components.fenetre.WindowScreen;
import connexion.DatabaseConnexion;
import controller.PasswordController;
import controller.WhiteListController;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class UpdateUserPanel extends JPanel{
    private final int panelW;
    private final int panelH;
    public UpdateUserPanel(JFrame frame, JPanel pan, int panelW, int panelH, int id) {
        super(null);
        this.panelW = panelW;
        this.panelH = panelH;

        int widthComponent = 150;
        int heightComponent = 30;

        ArrayList<String> test = DatabaseConnexion.getUserInfosFromId(id);
        //TEXTE
        JLabel infoLabel = new JLabel("Information utilisateur");
        JLabel emailLabel = new JLabel(test.get(0));
        JLabel passwordLabel = new JLabel("********");
        JLabel pseudoLabel = new JLabel(test.get(3));
        JLabel first_nameLabel = new JLabel(test.get(1));
        JLabel surnameLabel = new JLabel(test.get(2));

        //BUTTON
        JButton emailChange = new JButton("Modifier");
        JButton passwordChange = new JButton("Modifier");
        JButton pseudoChange = new JButton("Modifier");

        //FIELD
        JTextField emailField = new JTextField();
        JPasswordField passwordField = new JPasswordField();
        JPasswordField pseudoField = new JPasswordField();

        //POSITION LABEL
        infoLabel.setBounds((int) (this.panelW*0.8) - widthComponent/2, (int) (this.panelH*0.25) - heightComponent/2, widthComponent, heightComponent);
        first_nameLabel.setBounds((int) (this.panelW*0.5) - widthComponent/2, (int) (this.panelH*0.50) - heightComponent/2, widthComponent, heightComponent);
        surnameLabel.setBounds((int) (this.panelW*0.5) - widthComponent/2, (int) (this.panelH*0.75) - heightComponent/2, widthComponent, heightComponent);
        emailLabel.setBounds((int) (this.panelW*0.5) - widthComponent/2, (int) (this.panelH) - heightComponent/2, widthComponent, heightComponent);
        passwordLabel.setBounds((int) (this.panelW*0.5) - widthComponent/2, (int) (this.panelH*1.25) - heightComponent/2, widthComponent, heightComponent);
        pseudoLabel.setBounds((int) (this.panelW*0.5) - widthComponent/2, (int) (this.panelH*1.50) - heightComponent/2, widthComponent, heightComponent);


        //POSITION CHANGE
        emailChange.setBounds((int) (this.panelW*1.1) - widthComponent/2, (int) (this.panelH) - heightComponent/2, widthComponent, heightComponent);
        passwordChange.setBounds((int) (this.panelW*1.1) - widthComponent/2, (int) (this.panelH*1.25) - heightComponent/2, widthComponent, heightComponent);
        pseudoChange.setBounds((int) (this.panelW*1.1) - widthComponent/2, (int) (this.panelH*1.50) - heightComponent/2, widthComponent, heightComponent);

        //POSITION FIELD
        emailField.setBounds((int) (this.panelW*0.6) - widthComponent/2, (int) (this.panelH) - heightComponent/2, widthComponent, heightComponent);
        passwordField.setBounds((int) (this.panelW*0.6) - widthComponent/2, (int) (this.panelH*1.25) - heightComponent/2, widthComponent, heightComponent);
        pseudoField.setBounds((int) (this.panelW*0.6) - widthComponent/2, (int) (this.panelH*1.50) - heightComponent/2, widthComponent, heightComponent);

        //LABEL
        this.add(infoLabel);
        this.add(emailLabel);
        this.add(passwordLabel);
        this.add(pseudoLabel);
        this.add(first_nameLabel);
        this.add(surnameLabel);

        //CHANGE
        this.add(passwordChange);
        this.add(emailChange);
        this.add(pseudoChange);

        //FIELD
        this.add(passwordField);
        this.add(emailField);
        this.add(pseudoField);

        //INVISIBLE FILED
        emailField.setVisible(false);
        passwordField.setVisible(false);
        pseudoField.setVisible(false);

        //BG COLOR
        this.setBackground(Color.gray);

        //CONDITION EMAIL
        emailChange.addActionListener(event -> {
            ArrayList<String> whiteListElements = WhiteListController.getElements();
            String email = JOptionPane.showInputDialog(frame, "Renseignez le nouvel email :");

            if(email != null) {
                if(email.matches("([a-zA-Z]{1,30}(.|)[a-zA-Z]{1,30}@[a-zA-Z]{1,30}.(com|fr))") && whiteListElements.contains(email)) {
                    int idUser = WindowScreen.idTmp != 0 ? WindowScreen.idTmp : WindowScreen.userId;
                    DatabaseConnexion.updateEmail(email, idUser);
                    WindowScreen.pageUpdateRedraw(frame, pan, panelW, panelH, idUser);
                } else {
                    JOptionPane.showMessageDialog(frame, "L'email est incorrect ou n'a pas été validé par l'administrateur", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }

        });

        //CONDITION PASSWORD
        passwordChange.addActionListener(event -> {
            String password = JOptionPane.showInputDialog(frame, "Renseignez votre nouveau mot de passe :");
            if(password != null) {
                String confirmation = JOptionPane.showInputDialog(frame, "Confirmation du mot de passe :");
                if(password.matches(confirmation)) {
                    int idUser = WindowScreen.idTmp != 0 ? WindowScreen.idTmp : WindowScreen.userId;
                    DatabaseConnexion.updatePassword(PasswordController.hashPassword(password), idUser);
                } else {
                    JOptionPane.showMessageDialog(frame, "Les deux mots de passes ne correspondent pas", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }

        });

        //CONDITION PSEUDO
        pseudoChange.addActionListener(event -> {
            String pseudo = JOptionPane.showInputDialog(frame, "Renseignez votre nouveau pseudo :");
            if(pseudo != null) {
                int idUser = WindowScreen.idTmp != 0 ? WindowScreen.idTmp : WindowScreen.userId;
                DatabaseConnexion.updatePseudo(pseudo, idUser);
                WindowScreen.pageUpdateRedraw(frame, pan, panelW, panelH, idUser);
            }
        });

    }
}