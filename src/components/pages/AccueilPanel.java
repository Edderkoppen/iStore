package components.pages;

import javax.swing.*;
import java.awt.*;

public class AccueilPanel extends JPanel {

    private int width;
    private int height;
    private JPanel panelRight;
    private JPanel panelLeft;
    private JFrame frame;

    public AccueilPanel(int width, int height,JFrame frame, JPanel panelRight, JPanel panelLeft) {

        super(new BorderLayout());
        this.width = width;
        this.height = height;
        this.frame = frame;
        this.panelRight = panelRight;
        this.panelLeft = panelLeft;

        panelLeft.setPreferredSize(new Dimension(width/2, height));
        panelRight.setPreferredSize(new Dimension(width/2, height));

        this.add(panelLeft, BorderLayout.WEST);
        this.add(panelRight, BorderLayout.EAST);

    }
    public static void main(String[] args) {
        JFrame frame = new JFrame();

        Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        int width  = (int)dimension.getWidth();
        int height = (int)dimension.getHeight();

        frame.setSize(width*3/4, height*3/4);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        JPanel contentPane = (JPanel) frame.getContentPane();


        // contentPane.add(new ConnexionPanel(frame, width, height, contentPane));

        AccueilPanel main = new AccueilPanel(width*3/4,height*3/4, frame, new PageInscriptionPanel((width*3/4)/2, height*3/4), new ConnexionPanel((width*3/4)/2, height*3/4, contentPane));
        frame.add(main);
        frame.setVisible(true);
    }

}
