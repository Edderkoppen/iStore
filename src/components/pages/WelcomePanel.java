package components.pages;

import javax.swing.*;
import java.awt.*;

public class WelcomePanel extends JPanel {
    private int width;
    private int height;
    private JPanel panelRight;
    private JPanel panelLeft;
    private JFrame frame;

    public WelcomePanel(int width, int height, JFrame frame, JPanel panelRight, JPanel panelLeft) {

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

}

