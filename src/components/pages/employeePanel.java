package components.pages;

import javax.swing.*;
import java.awt.*;

public class employeePanel extends JPanel{
    private final int panelW;
    private final int panelH;
    private JFrame frame;

    public employeePanel(int panelW, int panelH) {
        super(null);
        this.panelW = panelW;
        this.panelH = panelH;

        int widthComponent = 70;
        int heightComponent = 30;

        JLabel emailLabel = new JLabel("email");
        JLabel passwordLabel = new JLabel("password");
        JLabel pseudoLabel = new JLabel("pseudo");
        JLabel first_nameLabel = new JLabel("first_name");
        JLabel surnameLabel = new JLabel("surname");

        //Position
        emailLabel.setBounds((int) (this.panelW*0.35) - widthComponent/2, (int) (this.panelH*0.35) - heightComponent/2, widthComponent, heightComponent);
        passwordLabel.setBounds((int) (this.panelW*0.45) - widthComponent/2, (int) (this.panelH*0.35) - heightComponent/2, widthComponent, heightComponent);
        pseudoLabel.setBounds((int) (this.panelW*0.55) - widthComponent/2, (int) (this.panelH*0.35) - heightComponent/2, widthComponent, heightComponent);
        first_nameLabel.setBounds((int) (this.panelW*0.65) - widthComponent/2, (int) (this.panelH*0.35) - heightComponent/2, widthComponent, heightComponent);
        surnameLabel.setBounds((int) (this.panelW*0.75) - widthComponent/2, (int) (this.panelH*0.35) - heightComponent/2, widthComponent, heightComponent);

        this.add(emailLabel);
        this.add(passwordLabel);
        this.add(pseudoLabel);
        this.add(first_nameLabel);
        this.add(surnameLabel);

        this.setBackground(Color.white);
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


        employeePanel main = new employeePanel(panelW*3/4,panelH*3/4);
        frame.add(main);
        frame.setVisible(true);
    }
}
