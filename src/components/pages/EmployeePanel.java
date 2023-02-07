package components.pages;

import javax.swing.*;
import java.awt.*;

public class EmployeePanel extends JPanel{
    private final int panelW;
    private final int panelH;
    private JPanel pan;

    public EmployeePanel(int panelW, int panelH, JPanel pan) {
        super(null);
        this.panelW = panelW;
        this.panelH = panelH;
        this.pan = pan;

        int widthComponent = 70;
        int heightComponent = 30;

        JLabel emailLabel = new JLabel("email");
        JLabel passwordLabel = new JLabel("password");
        JLabel pseudoLabel = new JLabel("pseudo");
        JLabel first_nameLabel = new JLabel("first_name");
        JLabel surnameLabel = new JLabel("surname");


        emailLabel.setBounds((int) (this.panelW*0.1) - widthComponent/2, (int) (this.panelH*0.5) - heightComponent/2, widthComponent, heightComponent);
        passwordLabel.setBounds((int) (this.panelW*0.2) - widthComponent/2, (int) (this.panelH*0.5) - heightComponent/2, widthComponent, heightComponent);
        pseudoLabel.setBounds((int) (this.panelW*0.3) - widthComponent/2, (int) (this.panelH*0.5) - heightComponent/2, widthComponent, heightComponent);
        first_nameLabel.setBounds((int) (this.panelW*0.4) - widthComponent/2, (int) (this.panelH*0.5) - heightComponent/2, widthComponent, heightComponent);
        surnameLabel.setBounds((int) (this.panelW*0.5) - widthComponent/2, (int) (this.panelH*0.5) - heightComponent/2, widthComponent, heightComponent);

        this.add(emailLabel);
        this.add(passwordLabel);
        this.add(pseudoLabel);
        this.add(first_nameLabel);
        this.add(surnameLabel);

        this.setBackground(Color.white);
    }
}
