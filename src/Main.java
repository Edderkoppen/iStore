import components.fenetre.WindowFrame;
import components.menus.MenuBarSample;

import javax.swing.*;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import java.awt.Dimension;

public class Main {
    public static void main(String[] args) throws UnsupportedLookAndFeelException {

        Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        int width  = (int)dimension.getWidth();
        int height = (int)dimension.getHeight();

        UIManager.setLookAndFeel(new NimbusLookAndFeel());
        MenuBarSample window = new MenuBarSample(width, height);
        window.setVisible(true);

    }
}