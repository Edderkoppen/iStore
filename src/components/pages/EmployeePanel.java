package components.pages;

import connexion.DatabaseConnexion;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class EmployeePanel extends JPanel{
    private final int panelW;
    private final int panelH;
    private JPanel pan;

    public EmployeePanel(int panelW, int panelH, JPanel pan, String firstName, String lastName) {
        super(null);
        this.panelW = panelW;
        this.panelH = panelH;
        this.pan = pan;

        int widthComponent = 200;
        int heightComponent = 30;

        ArrayList<String> infos = DatabaseConnexion.getUserInfos(firstName, lastName);

        JLabel emailLabel = new JLabel(infos.get(0));
        JLabel first_nameLabel = new JLabel(infos.get(1));
        JLabel surnameLabel = new JLabel(infos.get(2));

        emailLabel.setBounds((int) (this.panelW*0.9) - widthComponent/2, (int) (this.panelH*0.35) - heightComponent/2, widthComponent, heightComponent);
        first_nameLabel.setBounds((int) (this.panelW*1.6) - widthComponent/2, (int) (this.panelH*0.35) - heightComponent/2, widthComponent, heightComponent);
        surnameLabel.setBounds((int) (this.panelW*1.9) - widthComponent/2, (int) (this.panelH*0.35) - heightComponent/2, widthComponent, heightComponent);

        this.add(emailLabel);
        this.add(first_nameLabel);
        this.add(surnameLabel);

        this.setBackground(Color.white);
    }
}
