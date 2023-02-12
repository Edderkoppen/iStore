package istore.components.pages;

import javax.swing.*;
import java.awt.*;

public class WelcomePage extends JPanel {
    private final int width;
    private final int height;
    private final JPanel panelRight;
    private final JPanel panelLeft;

    public WelcomePage(int width, int height, JPanel panelRight, JPanel panelLeft) {

        super(new BorderLayout());
        this.width = width;
        this.height = height;
        this.panelRight = panelRight;
        this.panelLeft = panelLeft;

        this.panelLeft.setPreferredSize(new Dimension(this.width/2, this.height));
        this.panelRight.setPreferredSize(new Dimension(this.width/2, this.height));

        this.add(this.panelLeft, BorderLayout.WEST);
        this.add(this.panelRight, BorderLayout.EAST);

    }

}

