package components.pages;

import components.screen.WindowScreen;
import controller.DatabaseConnexionController;
import controller.PasswordController;
import controller.WhiteListController;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class UpdateUserPage extends JPanel{
    private final int panelW;
    private final int panelH;
    private final JFrame frame;
    private final JPanel pan;
    private final int id;
    public UpdateUserPage(JFrame frame, JPanel pan, int panelW, int panelH, int id) {
        super(null);
        this.panelW = panelW;
        this.panelH = panelH;
        this.frame = frame;
        this.pan = pan;
        this.id = id;


        int widthComponent = 150;
        int heightComponent = 30;

        ArrayList<String> userInfos = DatabaseConnexionController.getUserInfosFromId(this.id);

        JLabel infoLabel = new JLabel("Information utilisateur");
        JLabel emailLabel = new JLabel(userInfos.get(0));
        JLabel passwordLabel = new JLabel("********");
        JLabel pseudoLabel = new JLabel(userInfos.get(3));
        JLabel first_nameLabel = new JLabel(userInfos.get(1));
        JLabel surnameLabel = new JLabel(userInfos.get(2));

        JButton emailChange = new JButton("Modifier");
        JButton passwordChange = new JButton("Modifier");
        JButton pseudoChange = new JButton("Modifier");

        JTextField emailField = new JTextField();
        JPasswordField passwordField = new JPasswordField();
        JPasswordField pseudoField = new JPasswordField();

        infoLabel.setBounds((int) (this.panelW*0.8) - widthComponent/2, (int) (this.panelH*0.25) - heightComponent/2, widthComponent, heightComponent);
        first_nameLabel.setBounds((int) (this.panelW*0.5) - widthComponent/2, (int) (this.panelH*0.50) - heightComponent/2, widthComponent, heightComponent);
        surnameLabel.setBounds((int) (this.panelW*0.5) - widthComponent/2, (int) (this.panelH*0.75) - heightComponent/2, widthComponent, heightComponent);
        emailLabel.setBounds((int) (this.panelW*0.5) - widthComponent/2, this.panelH - heightComponent/2, widthComponent, heightComponent);
        passwordLabel.setBounds((int) (this.panelW*0.5) - widthComponent/2, (int) (this.panelH*1.25) - heightComponent/2, widthComponent, heightComponent);
        pseudoLabel.setBounds((int) (this.panelW*0.5) - widthComponent/2, (int) (this.panelH*1.50) - heightComponent/2, widthComponent, heightComponent);

        emailChange.setBounds((int) (this.panelW*1.1) - widthComponent/2, this.panelH - heightComponent/2, widthComponent, heightComponent);
        passwordChange.setBounds((int) (this.panelW*1.1) - widthComponent/2, (int) (this.panelH*1.25) - heightComponent/2, widthComponent, heightComponent);
        pseudoChange.setBounds((int) (this.panelW*1.1) - widthComponent/2, (int) (this.panelH*1.50) - heightComponent/2, widthComponent, heightComponent);

        emailField.setBounds((int) (this.panelW*0.6) - widthComponent/2, this.panelH - heightComponent/2, widthComponent, heightComponent);
        passwordField.setBounds((int) (this.panelW*0.6) - widthComponent/2, (int) (this.panelH*1.25) - heightComponent/2, widthComponent, heightComponent);
        pseudoField.setBounds((int) (this.panelW*0.6) - widthComponent/2, (int) (this.panelH*1.50) - heightComponent/2, widthComponent, heightComponent);

        this.add(infoLabel);
        this.add(emailLabel);
        this.add(passwordLabel);
        this.add(pseudoLabel);
        this.add(first_nameLabel);
        this.add(surnameLabel);

        this.add(passwordChange);
        this.add(emailChange);
        this.add(pseudoChange);

        this.add(passwordField);
        this.add(emailField);
        this.add(pseudoField);

        emailField.setVisible(false);
        passwordField.setVisible(false);
        pseudoField.setVisible(false);

        this.setBackground(Color.gray);

        emailChange.addActionListener(event -> {
            ArrayList<String> whiteListElements = WhiteListController.getElements();
            String email = JOptionPane.showInputDialog(this.frame, "Renseignez le nouvel email :");

            if(email != null) {
                if(email.matches("([a-zA-Z]{1,30}(.|)[a-zA-Z]{1,30}@[a-zA-Z]{1,30}.(com|fr))") && whiteListElements.contains(email)) {
                    int idUser = WindowScreen.idTmp != 0 ? WindowScreen.idTmp : WindowScreen.userId;
                    DatabaseConnexionController.updateEmail(email, idUser);
                    WindowScreen.pageUpdateRedraw(this.frame, this.pan, this.panelW, this.panelH, idUser);
                } else {
                    JOptionPane.showMessageDialog(this.frame, "L'email est incorrect ou n'a pas été validé par l'administrateur", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        passwordChange.addActionListener(event -> {
            String password = JOptionPane.showInputDialog(this.frame, "Renseignez votre nouveau mot de passe :");
            if(password != null) {
                String confirmation = JOptionPane.showInputDialog(this.frame, "Confirmation du mot de passe :");
                if(password.matches(confirmation)) {
                    int idUser = WindowScreen.idTmp != 0 ? WindowScreen.idTmp : WindowScreen.userId;
                    DatabaseConnexionController.updatePassword(PasswordController.hashPassword(password), idUser);
                } else {
                    JOptionPane.showMessageDialog(this.frame, "Les deux mots de passes ne correspondent pas", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        pseudoChange.addActionListener(event -> {
            String pseudo = JOptionPane.showInputDialog(this.frame, "Renseignez votre nouveau pseudo :");
            if(pseudo != null) {
                int idUser = WindowScreen.idTmp != 0 ? WindowScreen.idTmp : WindowScreen.userId;
                DatabaseConnexionController.updatePseudo(pseudo, idUser);
                WindowScreen.pageUpdateRedraw(this.frame, this.pan, this.panelW, this.panelH, idUser);
            }
        });
    }
}