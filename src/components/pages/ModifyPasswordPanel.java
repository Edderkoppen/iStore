package components.pages;

        import javax.swing.*;
        import java.awt.*;

public class ModifyPasswordPanel extends JPanel {
    private final int panelW;
    private final int panelH;
    private JFrame frame;
    public ModifyPasswordPanel(int panelW, int panelH) {
        super(null);
        this.panelW = panelW;
        this.panelH = panelH;

        int widthComponent = 150;
        int heightComponent = 30;

        JLabel infoLabel = new JLabel("Modification");
        JLabel emailLabel = new JLabel("email");
        JLabel passwordLabel = new JLabel("password");
        JLabel pseudoLabel = new JLabel("pseudo");
        JLabel first_nameLabel = new JLabel("first_name");
        JLabel surnameLabel = new JLabel("surname");

        //Position
        infoLabel.setBounds((int) (this.panelW*0.5) - widthComponent/2, (int) (this.panelH*0.15) - heightComponent/2, widthComponent, heightComponent);
        emailLabel.setBounds((int) (this.panelW*0.4) - widthComponent/2, (int) (this.panelH*0.41) - heightComponent/2, widthComponent, heightComponent);
        passwordLabel.setBounds((int) (this.panelW*0.4) - widthComponent/2, (int) (this.panelH*0.54) - heightComponent/2, widthComponent, heightComponent);
        pseudoLabel.setBounds((int) (this.panelW*0.4) - widthComponent/2, (int) (this.panelH*0.67) - heightComponent/2, widthComponent, heightComponent);
        first_nameLabel.setBounds((int) (this.panelW*0.65) - widthComponent/2, (int) (this.panelH*0.28) - heightComponent/2, widthComponent, heightComponent);
        surnameLabel.setBounds((int) (this.panelW*0.4) - widthComponent/2, (int) (this.panelH*0.28) - heightComponent/2, widthComponent, heightComponent);


        //0.80 0.67
        this.add(emailLabel);
        this.add(passwordLabel);
        this.add(pseudoLabel);
        this.add(first_nameLabel);
        this.add(surnameLabel);

        JButton emailChange = new JButton("Modifier");
        emailChange.setBounds((int) (this.panelW*0.6) - widthComponent/2, (int) (this.panelH*0.41) - heightComponent/2, widthComponent, heightComponent);
        this.add(emailChange);

        JButton passwordChange = new JButton("Modifier");
        passwordChange.setBounds((int) (this.panelW*0.6) - widthComponent/2, (int) (this.panelH*0.54) - heightComponent/2, widthComponent, heightComponent);
        this.add(passwordChange);

        JButton pseudoChange = new JButton("Modifier");
        pseudoChange.setBounds((int) (this.panelW*0.6) - widthComponent/2, (int) (this.panelH*0.67) - heightComponent/2, widthComponent, heightComponent);
        this.add(pseudoChange);


        this.setBackground(Color.gray);
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
