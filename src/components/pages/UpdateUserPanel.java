package components.pages;

import components.fenetre.WindowScreen;
import connexion.DatabaseConnexion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.reflect.Field;
import java.util.ArrayList;


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
        JLabel first_nameLabel = new JLabel("first_name");
        JLabel pseudoLabel = new JLabel("pseudo");
        JLabel emailLabel = new JLabel("email");
        JLabel passwordLabel = new JLabel("password");
        JLabel passwordConfirmedLabel = new JLabel("Confirmation:");
        JLabel surnameLabel = new JLabel("surname");
        JButton submit = new JButton("Sauvegarder");


        //BASE DE DONNEE
        //DatabaseConnexion.getUserPrincipalInfos(WindowScreen.userId);
        //ArrayList<String>  list = DatabaseConnexion.getUserPrincipalInfos(2);
        //System.out.println(list);


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


        //CONDITION PASSWORD


        //CONDITION CONRFIMED PASSWORD


        //CONDITION PSEUDO
        pseudoChange.addActionListener(event -> {
            String pseudo = JOptionPane.showInputDialog(frame, "Renseignez votre nouveau pseudo :");
            if (pseudo == null || pseudo.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Ne pas laisser vide .", "Erreur", JOptionPane.ERROR_MESSAGE);
            } else {
            }
        });


        //CONDITION ENVOYER
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


    public static void main(String[] args) {
        JFrame frame = new JFrame();

        Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        int panelW  = (int)dimension.getWidth();
        int panelH = (int)dimension.getHeight();

        frame.setSize(panelW*3/4, panelH*3/4);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        UpdateUserPanel main = new UpdateUserPanel(panelW*3/4,panelH*3/4);
        frame.add(main);
        frame.setVisible(true);

    }
}
